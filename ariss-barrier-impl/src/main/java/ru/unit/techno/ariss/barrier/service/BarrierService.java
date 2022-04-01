package ru.unit.techno.ariss.barrier.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDtoUi;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.barrier.api.enums.BarrierResponseStatus;
import ru.unit.techno.ariss.log.action.lib.api.LogActionBuilder;
import ru.unit.techno.ariss.log.action.lib.entity.Description;
import ru.unit.techno.ariss.log.action.lib.model.ActionStatus;
import ru.unit.techno.device.registration.api.DeviceResource;
import ru.unit.techno.device.registration.api.dto.DeviceResponseDto;
import ru.unit.techno.device.registration.api.enums.DeviceType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class BarrierService {

    private final LogActionBuilder logActionBuilder;
    private final DeviceResource deviceResource;

    public void forceOpen(BarrierRequestDtoUi request) {
        try {
            DeviceResponseDto group = deviceResource.getGroupDevices(request.getBarrierId(), DeviceType.ENTRY);
            requestToOpenBarrier(group);
            logActionBuilder.buildActionObjectAndLogAction(request.getBarrierId(),
                    null,
                    request.getGovernmentNumber(),
                    ActionStatus.UNKNOWN);
        } catch (Exception e) {
            logActionBuilder.buildActionObjectAndLogAction(request.getBarrierId(),
                    null,
                    request.getGovernmentNumber(),
                    ActionStatus.UNKNOWN,
                    true,
                    new Description().setStatusCode("500")
                            .setErroredServiceName("Ariss Barrier")
                            .setMessage(e.getMessage() != null ? e.getMessage() : "Internal error: " + e.getLocalizedMessage()));
        }
    }

    public BarrierResponseDto openBarrier(BarrierRequestDto inputRequest) {
        log.info("Received request to barrier open, request body: {}", inputRequest);
        try {
            DeviceResponseDto group = deviceResource.getGroupDevices(inputRequest.getBarrierId(), DeviceType.ENTRY);

            requestToOpenBarrier(group);

            logActionBuilder.buildActionObjectAndLogAction(inputRequest.getBarrierId(),
                    null,
                    inputRequest.getGovernmentNumber(),
                    ActionStatus.UNKNOWN);

            BarrierResponseDto barrierResponseDto = new BarrierResponseDto()
                    .setBarrierId(inputRequest.getBarrierId())
                    .setBarrierResponseStatus(BarrierResponseStatus.SUCCESS);
            log.info("Barrier successfully opened, barrier ID {}", inputRequest.getBarrierId());
            return barrierResponseDto;
        } catch (Exception e) {
            logActionBuilder.buildActionObjectAndLogAction(inputRequest.getBarrierId(),
                    0L,
                    inputRequest.getGovernmentNumber(),
                    ActionStatus.UNKNOWN,
                    true,
                    new Description().setStatusCode("500")
                            .setErroredServiceName("Ariss Barrier")
                            .setMessage(e.getMessage() != null ? e.getMessage() : "Internal error: " + e.getLocalizedMessage()));
            throw e;
        }
    }

    @SneakyThrows
    private void requestToOpenBarrier(DeviceResponseDto group) {
        log.info("start open barrier, deviceId: {}", group.getDeviceId());
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build()
                .send(HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI("http://" + group.getEntryAddress() + "/api/squd-core/barrier/open/" + group.getDeviceId()))
                        .build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("barrier not open");
        }
        log.info("response is {}", response);
    }
}

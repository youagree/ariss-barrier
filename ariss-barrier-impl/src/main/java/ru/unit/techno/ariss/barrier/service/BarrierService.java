package ru.unit.techno.ariss.barrier.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.barrier.api.enums.BarrierResponseStatus;
import ru.unit.techno.ariss.log.action.lib.api.LogActionBuilder;
import ru.unit.techno.ariss.log.action.lib.entity.Description;
import ru.unit.techno.ariss.log.action.lib.model.ActionStatus;

@Slf4j
@Service
@RequiredArgsConstructor
public class BarrierService {

    private final LogActionBuilder logActionBuilder;

    public BarrierResponseDto forceOpen(BarrierRequestDto request) {
        try {
            /// TODO: 13.09.2021 Какая то логика по форс опену
            logActionBuilder.buildActionObjectAndLogAction(request.getBarrierId(),
                    0L,
                    request.getGovernmentNumber(),
                    ActionStatus.UNKNOWN);
        } catch (Exception e) {
            logActionBuilder.buildActionObjectAndLogAction(request.getBarrierId(),
                    0L,
                    request.getGovernmentNumber(),
                    ActionStatus.UNKNOWN,
                    true,
                    new Description().setStatusCode("500")
                            .setErroredServiceName("Ariss Barrier")
                            .setMessage(e.getMessage() != null ? e.getMessage() : "Internal error: " + e.getLocalizedMessage()));
        }
        return new BarrierResponseDto();
    }

    public BarrierResponseDto openBarrier(BarrierRequestDto request) {
        log.info("Received request to barrier open, request body: {}", request);
        try {
            //TODO логика открытия шлагбаума
            //....какая то логика....
            BarrierResponseDto barrierResponseDto = new BarrierResponseDto()
                    .setBarrierId(request.getBarrierId())
                    .setBarrierResponseStatus(BarrierResponseStatus.SUCCESS);
            log.info("Barrier successfully opened, barrier ID {}", request.getBarrierId());
            return barrierResponseDto;
        } catch (Exception e) {
            logActionBuilder.buildActionObjectAndLogAction(request.getBarrierId(),
                    0L,
                    request.getGovernmentNumber(),
                    ActionStatus.UNKNOWN,
                    true,
                    new Description().setStatusCode("500")
                            .setErroredServiceName("Ariss Barrier")
                            .setMessage(e.getMessage() != null ? e.getMessage() : "Internal error: " + e.getLocalizedMessage()));
        }

        return null;
    }
}

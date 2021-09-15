
package ru.unit.techno.ariss.barrier.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.log.action.lib.api.LogActionBuilder;
import ru.unit.techno.ariss.log.action.lib.entity.Description;
import ru.unit.techno.ariss.log.action.lib.model.ActionStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/barrier")
public class BarrierUiResource {

    private final LogActionBuilder logActionBuilder;

    @PostMapping("/forceOpen")
    public BarrierResponseDto forceOpen(@RequestBody BarrierRequestDto request) {
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
}
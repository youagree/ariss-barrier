
package ru.unit.techno.ariss.barrier.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.barrier.service.BarrierService;
import ru.unit.techno.ariss.log.action.lib.api.LogActionBuilder;
import ru.unit.techno.ariss.log.action.lib.entity.Description;
import ru.unit.techno.ariss.log.action.lib.model.ActionStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/barrier")
public class BarrierUiResource {

    private final BarrierService barrierService;

    @PostMapping("/forceOpen")
    public BarrierResponseDto forceOpen(@RequestBody BarrierRequestDto request) {
        return barrierService.forceOpen(request);
    }
}
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

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/barrier")
public class BarrierApiResource {

    private final BarrierService barrierService;

    @PostMapping("/open")
    public BarrierResponseDto openBarrier(@Valid @RequestBody BarrierRequestDto request) {
        return barrierService.openBarrier(request);
    }
}

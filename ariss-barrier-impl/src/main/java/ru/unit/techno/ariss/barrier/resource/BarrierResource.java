package ru.unit.techno.ariss.barrier.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.barrier.api.enums.BarrierResponseStatus;

@Slf4j
@RestController
@RequestMapping("/api/barrier")
public class BarrierResource {

    @PostMapping("/open")
    public BarrierResponseDto openBarrier(@RequestBody BarrierRequestDto request) {
        log.info("Received request to barrier open, request body: {}", request);

        //TODO логика открытия шлагбаума
        //....какая то логика....
        BarrierResponseDto barrierResponseDto = new BarrierResponseDto()
                .setBarrierId(request.getBarrierId())
                .setBarrierResponseStatus(BarrierResponseStatus.SUCCESS);
        log.info("Barrier successfully opened, barrier ID {}", request.getBarrierId());
        return barrierResponseDto;
    }
}

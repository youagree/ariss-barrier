package ru.unit.techno.ariss.barrier.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;
import ru.unit.techno.ariss.barrier.api.enums.BarrierResponseStatus;

@RestController
@RequestMapping("/api/barrier")
public class BarrierResource {

    @PostMapping("/open")
    public BarrierResponseDto openBarrier(@RequestBody BarrierRequestDto request) {
        System.out.println(request);

        return new BarrierResponseDto()
                .setBarrierId(request.getBarrierId())
                .setBarrierResponseStatus(BarrierResponseStatus.SUCCESS);
    }
}

package ru.unit.techno.ariss.barrier.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDto;
import ru.unit.techno.ariss.barrier.api.dto.BarrierResponseDto;

@FeignClient(name = "ariss-barrier")
public interface BarrierFeignClient {

    @PostMapping(value = "/api/barrier/open")
    BarrierResponseDto openBarrier(@RequestBody BarrierRequestDto request);
}

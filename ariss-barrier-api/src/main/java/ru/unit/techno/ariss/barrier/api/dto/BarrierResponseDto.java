package ru.unit.techno.ariss.barrier.api.dto;

import lombok.Data;
import ru.unit.techno.ariss.barrier.api.enums.BarrierResponseStatus;

@Data
public class BarrierResponseDto {

    private Long barrierId;
    private BarrierResponseStatus barrierResponseStatus;
}

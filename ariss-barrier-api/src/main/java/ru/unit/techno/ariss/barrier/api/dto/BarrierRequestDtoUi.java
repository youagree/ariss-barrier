
package ru.unit.techno.ariss.barrier.api.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class BarrierRequestDtoUi {
    private Long barrierId;
    @Pattern(regexp = "(^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3})|(^[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)\\d{2,3})|(^[АВЕКМНОРСТУХ]{2}\\d{4}(?<!0000)\\d{2,3})|(^\\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2}\\d{2,3})|(^[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)[АВЕКМНОРСТУХ]\\d{2,3})$")
    private String governmentNumber;
}
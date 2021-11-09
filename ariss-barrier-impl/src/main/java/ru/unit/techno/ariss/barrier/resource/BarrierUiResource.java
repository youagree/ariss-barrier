
package ru.unit.techno.ariss.barrier.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unit.techno.ariss.barrier.api.dto.BarrierRequestDtoUi;
import ru.unit.techno.ariss.barrier.service.BarrierService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ui/barrier")
public class BarrierUiResource {

    private final BarrierService barrierService;

    @PostMapping("/forceOpen")
    public void forceOpen(@RequestBody @Valid BarrierRequestDtoUi request) {
        barrierService.forceOpen(request);
    }
}
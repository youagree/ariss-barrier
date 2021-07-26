package ru.unit.techno.ariss.barrier.api.enums;

import lombok.Getter;

@Getter
public enum BarrierResponseStatus {
    SUCCESS("Barrier open successfully"), ERROR("Barrier not opened, some problems");

    @Getter
    private final String value;

    BarrierResponseStatus(String value) {
        this.value = value;
    }
}

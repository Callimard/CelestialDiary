package org.bandrsoftwares.celestialdiary.model.general.time;

import lombok.NonNull;

import java.time.LocalTime;

public record TimeInterval(@NonNull LocalTime start, @NonNull LocalTime end) {

    public TimeInterval {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("The TimeInterval BEGIN must be before the TimeInterval END");
        }
    }

    public boolean include(LocalTime time) {
        return (time.isAfter(start) || time.equals(start)) && (time.isBefore(end) || time.equals(end));
    }
}

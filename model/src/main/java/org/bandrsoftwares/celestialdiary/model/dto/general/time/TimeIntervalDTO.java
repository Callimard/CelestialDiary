package org.bandrsoftwares.celestialdiary.model.dto.general.time;

import org.bandrsoftwares.celestialdiary.model.general.time.TimeInterval;

import java.time.LocalTime;

public record TimeIntervalDTO(String start, String end) {

    public TimeIntervalDTO(TimeInterval timeInterval) {
        this(timeInterval.start().toString(), timeInterval.end().toString());
    }

    public TimeInterval toTimeInterval() {
        return new TimeInterval(LocalTime.parse(start), LocalTime.parse(end));
    }
}

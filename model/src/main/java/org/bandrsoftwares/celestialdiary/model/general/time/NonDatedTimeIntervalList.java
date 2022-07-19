package org.bandrsoftwares.celestialdiary.model.general.time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;


@ToString
@Getter
@NoArgsConstructor
public class NonDatedTimeIntervalList {

    // Variables.

    private Set<TimeInterval> timeIntervals;

    // Constructors.

    public NonDatedTimeIntervalList(@NonNull Set<TimeInterval> timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    // Methods.

    public boolean include(@NonNull LocalTime time) {
        for (TimeInterval timeInterval : timeIntervals) {
            if (timeInterval.include(time)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return timeIntervals.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NonDatedTimeIntervalList that)) return false;
        return timeIntervals.equals(that.timeIntervals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeIntervals);
    }
}

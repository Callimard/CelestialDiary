package org.bandrsoftwares.celestialdiary.model.general.time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.time.*;
import java.util.Objects;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor
public class DatedTimeIntervalList extends NonDatedTimeIntervalList {

    // Variables.

    private LocalDate date;

    // Constructors.

    public DatedTimeIntervalList(@NonNull LocalDate date, @NonNull Set<TimeInterval> timeIntervals) {
        super(timeIntervals);
        this.date = date;
    }

    // Methods.

    public boolean include(@NonNull LocalDateTime dateTime) {
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        if (date.equals(localDate)) {
            return include(time);
        } else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatedTimeIntervalList that)) return false;
        if (!super.equals(o)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
    }
}

package org.bandrsoftwares.celestialdiary.model.dto.general.time;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.general.time.NonDatedTimeIntervalList;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NonDatedTimeIntervalListDTO {

    private List<TimeIntervalDTO> timeIntervals;

    // Constructors.

    public NonDatedTimeIntervalListDTO() {
    }

    public NonDatedTimeIntervalListDTO(NonDatedTimeIntervalList nonDatedTimeIntervalList) {
        this(nonDatedTimeIntervalList.getTimeIntervals().stream().map(TimeIntervalDTO::new).toList());
    }

    // Methods.

    public NonDatedTimeIntervalList toNonDatedTimeIntervalList() {
        return new NonDatedTimeIntervalList(timeIntervals.stream().map(TimeIntervalDTO::toTimeInterval).collect(Collectors.toSet()));
    }
}

package org.bandrsoftwares.celestialdiary.model.dto.general.time;

import lombok.Getter;
import lombok.Setter;
import org.bandrsoftwares.celestialdiary.model.general.time.DatedTimeIntervalList;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Setter
@Getter
public class DatedTimeIntervalListDTO extends NonDatedTimeIntervalListDTO {

    private String date;

    // Constructors.

    public DatedTimeIntervalListDTO() {
        super();
    }

    public DatedTimeIntervalListDTO(DatedTimeIntervalList datedTimeIntervalList) {
        super(datedTimeIntervalList);
        this.date = datedTimeIntervalList.getDate().toString();
    }

    // Methods.

    public DatedTimeIntervalList toDatedTimeIntervalList() {
        return new DatedTimeIntervalList(LocalDate.parse(date), getTimeIntervals().stream().map(TimeIntervalDTO::toTimeInterval).collect(
                Collectors.toSet()));
    }
}

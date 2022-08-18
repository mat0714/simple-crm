package pl.mateuszkolodziejczyk.simplecrm.event.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventRequest {

    private LocalDate date;
    private String details;
}

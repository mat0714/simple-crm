package pl.mateuszkolodziejczyk.simplecrm.event.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventResponse {

    private Long id;
    private LocalDate date;
    private String details;
    private Customer customer;
}

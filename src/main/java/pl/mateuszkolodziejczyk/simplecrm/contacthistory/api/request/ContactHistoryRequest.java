package pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ContactHistoryRequest {

    private LocalDate Date;
    private String details;
    private Customer customer;
}

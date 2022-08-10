package pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ContactHistoryResponse {

    private Long id;
    private LocalDate Date;
    private String details;
    private Customer customer;
}

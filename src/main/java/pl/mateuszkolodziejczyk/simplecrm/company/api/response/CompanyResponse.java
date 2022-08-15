package pl.mateuszkolodziejczyk.simplecrm.company.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private Long id;
    private String name;
    private String streetNumber;
    private String streetName;
    private String city;
    private String voivodeship;
    private String zipCode;
    private Customer customer;
}

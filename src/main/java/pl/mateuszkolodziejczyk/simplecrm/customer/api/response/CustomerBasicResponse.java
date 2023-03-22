package pl.mateuszkolodziejczyk.simplecrm.customer.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;

@Getter
@AllArgsConstructor
public class CustomerBasicResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    private Company company;
}

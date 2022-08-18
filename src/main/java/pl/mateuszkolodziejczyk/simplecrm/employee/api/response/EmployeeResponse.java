package pl.mateuszkolodziejczyk.simplecrm.employee.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    private List<Customer> customers;
}

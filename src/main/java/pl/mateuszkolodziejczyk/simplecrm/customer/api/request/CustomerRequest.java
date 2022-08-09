package pl.mateuszkolodziejczyk.simplecrm.customer.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.ContactHistory;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;

@Getter
@AllArgsConstructor
public class CustomerRequest {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    private Company company;
    private ContactHistory contactHistory;
    private Employee employee;
}
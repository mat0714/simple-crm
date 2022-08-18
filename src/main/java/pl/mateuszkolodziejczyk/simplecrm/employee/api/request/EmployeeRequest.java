package pl.mateuszkolodziejczyk.simplecrm.employee.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmployeeRequest {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
}

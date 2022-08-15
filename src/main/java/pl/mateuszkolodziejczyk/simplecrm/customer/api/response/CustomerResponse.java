package pl.mateuszkolodziejczyk.simplecrm.customer.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    private Company company;
    private List<Event> event;
    private Employee employee;
}

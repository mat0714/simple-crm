package pl.mateuszkolodziejczyk.simplecrm.support;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;

@Component
public class EmployeeMapper {

    public Employee toEmployee(EmployeeRequest employeeRequest) {
        return new Employee(
                employeeRequest.getName(),
                employeeRequest.getSurname(),
                employeeRequest.getPhone(),
                employeeRequest.getEmail(),
                employeeRequest.getDepartment(),
                employeeRequest.getCustomers()
        );
    }

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getCustomers()
        );
    }

}

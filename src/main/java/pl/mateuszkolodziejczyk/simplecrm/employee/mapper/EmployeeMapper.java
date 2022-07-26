package pl.mateuszkolodziejczyk.simplecrm.employee.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;

@Component
public class EmployeeMapper {

    public Employee toEmployee(EmployeeRequest employeeRequest) {
        return new Employee(
                employeeRequest.getName(),
                employeeRequest.getSurname(),
                employeeRequest.getPhone(),
                employeeRequest.getEmail(),
                employeeRequest.getDepartment()
        );
    }

        public Employee toEmployee(Employee employee, EmployeeRequest employeeRequest) {
            employee.setName(employeeRequest.getName());
            employee.setSurname(employeeRequest.getSurname());
            employee.setPhone(employeeRequest.getPhone());
            employee.setEmail(employeeRequest.getEmail());
            employee.setDepartment(employeeRequest.getDepartment());
            return employee;
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

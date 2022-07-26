package pl.mateuszkolodziejczyk.simplecrm.service;

import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee doesn't exist"));
        return employee;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee doesn't exist"));
        employeeRepository.delete(employee);
    }
}

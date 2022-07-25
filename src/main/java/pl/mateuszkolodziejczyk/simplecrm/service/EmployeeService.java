package pl.mateuszkolodziejczyk.simplecrm.service;

import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}

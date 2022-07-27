package pl.mateuszkolodziejczyk.simplecrm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.repository.EmployeeRepository;
import pl.mateuszkolodziejczyk.simplecrm.support.EmployeeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.save(employeeMapper.toEmployee(employeeRequest));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public EmployeeResponse findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee doesn't exist"));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee doesn't exist"));
        employeeRepository.delete(employee);
    }
}

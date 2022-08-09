package pl.mateuszkolodziejczyk.simplecrm.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.employee.repository.EmployeeRepository;
import pl.mateuszkolodziejczyk.simplecrm.employee.mapper.EmployeeMapper;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

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
                ExceptionSupplier.employeeNotFound(id));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ExceptionSupplier.employeeNotFound(id));
        employeeRepository.save(employeeMapper.toEmployee(employee, employeeRequest));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ExceptionSupplier.employeeNotFound(id));
        employeeRepository.delete(employee);
    }
}

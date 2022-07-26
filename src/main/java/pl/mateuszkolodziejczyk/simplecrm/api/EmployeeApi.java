package pl.mateuszkolodziejczyk.simplecrm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employeeToSave) {
        Employee employee = employeeService.saveEmployee(employeeToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> allEmployees = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

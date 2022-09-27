package pl.mateuszkolodziejczyk.simplecrm.employee.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeApi {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Long> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Long id = employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        List<EmployeeResponse> allEmployees = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

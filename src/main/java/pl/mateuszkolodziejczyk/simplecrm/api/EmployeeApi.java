package pl.mateuszkolodziejczyk.simplecrm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.api.response.EmployeeResponse;
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
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse = employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
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
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

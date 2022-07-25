package pl.mateuszkolodziejczyk.simplecrm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateuszkolodziejczyk.simplecrm.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {

    EmployeeService employeeService;

    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> allEmployees = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    }
}

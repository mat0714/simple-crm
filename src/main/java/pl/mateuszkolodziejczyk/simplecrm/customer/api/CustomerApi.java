package pl.mateuszkolodziejczyk.simplecrm.customer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.service.CustomerService;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.request.EmployeeRequest;
import pl.mateuszkolodziejczyk.simplecrm.employee.api.response.EmployeeResponse;
import pl.mateuszkolodziejczyk.simplecrm.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.findCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<CustomerResponse> allCustomers = customerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

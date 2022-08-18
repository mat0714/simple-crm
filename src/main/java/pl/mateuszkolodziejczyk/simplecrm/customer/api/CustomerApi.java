package pl.mateuszkolodziejczyk.simplecrm.customer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.service.CustomerService;

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

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable Long customerId) {
        CustomerResponse customerResponse = customerService.findCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<CustomerResponse> allCustomers = customerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(customerId, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{customerId}/{employeeId}")
    public ResponseEntity<CustomerResponse> assignEmployeeToCustomer(
            @PathVariable Long customerId, @PathVariable Long employeeId) {
        CustomerResponse customerResponse = customerService.assignEmployeeToCustomer(customerId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }
}

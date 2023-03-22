package pl.mateuszkolodziejczyk.simplecrm.customer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerFullResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerBasicResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Long> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        Long id = customerService.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerFullResponse> findCustomer(@PathVariable Long customerId) {
        CustomerFullResponse customerFullResponse = customerService.findCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerFullResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerBasicResponse>> findAllCustomers(
            @RequestParam int page, @RequestParam int size) {
        Page<CustomerBasicResponse> allCustomers = customerService.findAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        customerService.updateCustomer(customerId, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{customerId}/{employeeId}")
    public ResponseEntity<Void> assignEmployeeToCustomer(
            @PathVariable Long customerId, @PathVariable Long employeeId) {
        customerService.assignEmployeeToCustomer(customerId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

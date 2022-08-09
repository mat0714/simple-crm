package pl.mateuszkolodziejczyk.simplecrm.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.customer.support.CustomerMapper;
import pl.mateuszkolodziejczyk.simplecrm.employee.support.EmployeeExceptionSupplier;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                EmployeeExceptionSupplier.itemNotFound(id));
        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse).collect(Collectors.toList());
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                EmployeeExceptionSupplier.itemNotFound(id));
        customerRepository.save(customerMapper.toCustomer(customer, customerRequest));
        return customerMapper.toCustomerResponse(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                EmployeeExceptionSupplier.itemNotFound(id));
        customerRepository.delete(customer);
    }
}

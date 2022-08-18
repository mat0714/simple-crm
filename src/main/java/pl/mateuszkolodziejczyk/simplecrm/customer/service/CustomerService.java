package pl.mateuszkolodziejczyk.simplecrm.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.customer.mapper.CustomerMapper;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.employee.repository.EmployeeRepository;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        return customerMapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse).collect(Collectors.toList());
    }

    public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        customerRepository.save(customerMapper.toCustomer(customer, customerRequest));
        return customerMapper.toCustomerResponse(customer);
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        customerRepository.delete(customer);
    }

    public CustomerResponse assignEmployeeToCustomer(Long customerId, Long employeeId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ExceptionSupplier.employeeNotFound(employeeId));
        customer.setEmployee(employee);
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }
}

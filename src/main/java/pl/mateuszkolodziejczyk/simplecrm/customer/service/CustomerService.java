package pl.mateuszkolodziejczyk.simplecrm.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerFullResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerBasicResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.exception.CanNotDeleteCustomerException;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.customer.mapper.CustomerMapper;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.employee.repository.EmployeeRepository;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerMapper customerMapper;

    public Long saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public CustomerFullResponse findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        return customerMapper.toCustomerResponse(customer);
    }

    public Page<CustomerBasicResponse> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return customerRepository.findAllBy(pageRequest);
    }

    public void updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        customerRepository.save(customerMapper.toCustomer(customer, customerRequest));
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        boolean customerHasRelationship =
                !CollectionUtils.isEmpty(customer.getEvent()) || customer.getEmployee() != null;
        if (customerHasRelationship) {
            throw new CanNotDeleteCustomerException();
        }
        customerRepository.delete(customer);
    }

    public void assignEmployeeToCustomer(Long customerId, Long employeeId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ExceptionSupplier.employeeNotFound(employeeId));
        customer.setEmployee(employee);
        customerRepository.save(customer);
    }
}

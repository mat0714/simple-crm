package pl.mateuszkolodziejczyk.simplecrm.customer.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.request.CustomerRequest;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerFullResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        return new Customer(
                customerRequest.getName(),
                customerRequest.getSurname(),
                customerRequest.getPhone(),
                customerRequest.getEmail(),
                customerRequest.getDepartment(),
                customerRequest.getCompany(),
                customerRequest.getEmployee()
        );
    }

    public Customer toCustomer(Customer customer, CustomerRequest customerRequest) {
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customer.setPhone(customerRequest.getPhone());
        customer.setEmail(customerRequest.getEmail());
        customer.setDepartment(customerRequest.getDepartment());
        return customer;
    }

    public CustomerFullResponse toCustomerResponse(Customer customer) {
        return new CustomerFullResponse(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getDepartment(),
                customer.getCompany(),
                customer.getEvents(),
                customer.getEmployee()
        );
    }
}
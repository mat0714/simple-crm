package pl.mateuszkolodziejczyk.simplecrm.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain.ContactHistory;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.employee.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class InitialDataConfig implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        insertFirstDataCollection();
        insertSecondDataCollection();
    }

    private void insertFirstDataCollection() {
        Company company = new Company(
                "E CORP",
                "5",
                "Domaniewska",
                "Warsaw",
                "Mazowieckie",
                "02-672"
        );

        ContactHistory contact1 = new ContactHistory(
                LocalDate.of(2021, 7, 14),
                "Good meeting. Customer is really interested in our product."
        );

        ContactHistory contact2 = new ContactHistory(
                LocalDate.of(2021, 8, 10),
                "Customer is going to send order tomorrow."
        );

        List<ContactHistory> contactHistory = List.of(contact1, contact2);

        Employee employee = new Employee(
                "Marcin",
                "Kowalski",
                "+48 999 999 999",
                "mkowalski@company.pl",
                "Sales Department"
        );

        Customer customer = new Customer(
                "John",
                "Doe",
                "+48 555 555 555",
                "jdoe@ecorp.com",
                "R&D",
                company,
                contactHistory,
                employee
        );

        contact1.setCustomer(customer);
        contact2.setCustomer(customer);
        customerRepository.save(customer);
    }

    private void insertSecondDataCollection() {
        Company company = new Company(
                "S CORP",
                "100",
                "Żwirki i Wigury",
                "Warsaw",
                "Mazowieckie",
                "02-091"
        );

        ContactHistory contact1 = new ContactHistory(
                LocalDate.of(2022, 3, 4),
                "Very interested but not have time. Next contact in April."
        );

        ContactHistory contact2 = new ContactHistory(
                LocalDate.of(2022, 4, 10),
                "Presentation for customer was made. Want to buy next month."
        );

        ContactHistory contact3 = new ContactHistory(
                LocalDate.of(2022, 5, 5),
                "Sold our Mega Package."
        );

        List<ContactHistory> contactHistory = List.of(contact1, contact2, contact3);

        Employee employee = new Employee(
                "Paweł",
                "Prokop",
                "+48 999 888 888",
                "pprokop@company.pl",
                "Sales Department"
        );

        Customer customer = new Customer(
                "Richard",
                "Banks",
                "+48 111 111 111",
                "rbanks@ecorp.com",
                "Production Department",
                company,
                contactHistory,
                employee
        );

        contact1.setCustomer(customer);
        contact2.setCustomer(customer);
        contact3.setCustomer(customer);
        customerRepository.save(customer);
    }
}

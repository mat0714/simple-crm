package pl.mateuszkolodziejczyk.simplecrm.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.user.UserRole;
import pl.mateuszkolodziejczyk.simplecrm.user.domain.User;
import pl.mateuszkolodziejczyk.simplecrm.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class InitialDataConfig implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        saveFirstCustomerWithData();
        saveSecondCustomerWithData();
        saveFirstUser();
        saveSecondUser();
    }

    private void saveFirstCustomerWithData() {
        Company company = new Company(
                "E CORP",
                "5",
                "Domaniewska",
                "Warsaw",
                "Mazowieckie",
                "02-672"
        );

        Event event1 = new Event(
                LocalDate.of(2021, 7, 14),
                "Good meeting. Customer is really interested in our product."
        );

        Event event2 = new Event(
                LocalDate.of(2021, 8, 10),
                "Customer is going to send order tomorrow."
        );

        List<Event> event = List.of(event1, event2);

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
                event,
                employee
        );

        event1.setCustomer(customer);
        event2.setCustomer(customer);
        customerRepository.save(customer);
    }

    private void saveSecondCustomerWithData() {
        Company company = new Company(
                "S CORP",
                "100",
                "Żwirki i Wigury",
                "Warsaw",
                "Mazowieckie",
                "02-091"
        );

        Event event1 = new Event(
                LocalDate.of(2022, 3, 4),
                "Very interested but not have time. Next contact in April."
        );

        Event event2 = new Event(
                LocalDate.of(2022, 4, 10),
                "Presentation for customer was made. Want to buy next month."
        );

        Event event3 = new Event(
                LocalDate.of(2022, 5, 5),
                "Sold our Mega Package."
        );

        List<Event> event = List.of(event1, event2, event3);

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
                event,
                employee
        );

        event1.setCustomer(customer);
        event2.setCustomer(customer);
        event3.setCustomer(customer);
        customerRepository.save(customer);
    }

    private void saveFirstUser() {
        List<String> roles = List.of("ROLE_" + UserRole.MANAGER.name());
        User user = new User(
                "Manager",
                passwordEncoder.encode("Password123"),
                roles,
                true,
                true,
                true,
                true);
        userRepository.save(user);
    }

    private void saveSecondUser() {
        List<String> roles = List.of("ROLE_" + UserRole.EMPLOYEE.name());
        User user = new User(
                "Employee",
                passwordEncoder.encode("Password123"),
                roles,
                true,
                true,
                true,
                true);
        userRepository.save(user);
    }
}

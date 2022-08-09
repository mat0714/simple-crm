package pl.mateuszkolodziejczyk.simplecrm.customer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.ContactHistory;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;

import javax.persistence.*;

@Entity(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    @OneToOne
    private Company company;
    @OneToOne
    private ContactHistory contactHistory;
    @ManyToOne
    private Employee employee;

    public Customer(String name, String surname, String phone, String email, String department, Company company, ContactHistory contactHistory, Employee employee) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.company = company;
        this.contactHistory = contactHistory;
        this.employee = employee;
    }
}

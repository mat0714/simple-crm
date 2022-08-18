package pl.mateuszkolodziejczyk.simplecrm.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Company company;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "customer")
    @JsonIgnore
    private List<Event> event;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Employee employee;

    public Customer(String name, String surname, String phone, String email, String department, Company company,
                    List<Event> event, Employee employee) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.company = company;
        this.event = event;
        this.employee = employee;
    }

    public Customer(String name, String surname, String phone, String email, String department, Company company,
                    Employee employee) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.company = company;
        this.employee = employee;
    }
}

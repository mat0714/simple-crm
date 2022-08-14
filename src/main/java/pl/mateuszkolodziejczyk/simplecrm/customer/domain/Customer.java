package pl.mateuszkolodziejczyk.simplecrm.customer.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain.ContactHistory;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<ContactHistory> contactHistory;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Employee employee;

    public Customer(String name, String surname, String phone, String email, String department, Company company,
                    List<ContactHistory> contactHistory, Employee employee) {
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

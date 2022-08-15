package pl.mateuszkolodziejczyk.simplecrm.employee.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String department;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Customer> customers;

    public Employee(String name, String surname, String phone, String email, String department) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }
}
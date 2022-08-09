package pl.mateuszkolodziejczyk.simplecrm.employee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import javax.persistence.*;

@Entity(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;
    @OneToOne(mappedBy = "company")
    private Customer customer;

}

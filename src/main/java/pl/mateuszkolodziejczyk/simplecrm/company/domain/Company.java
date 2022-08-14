package pl.mateuszkolodziejczyk.simplecrm.company.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Customer customer;

    public Company(String name, String streetNumber, String streetName, String city, String state, String zipCode) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}

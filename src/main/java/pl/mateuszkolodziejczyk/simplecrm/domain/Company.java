package pl.mateuszkolodziejczyk.simplecrm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

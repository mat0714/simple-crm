package pl.mateuszkolodziejczyk.simplecrm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}

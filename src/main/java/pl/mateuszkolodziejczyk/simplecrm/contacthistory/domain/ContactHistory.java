package pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContactHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String details;
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Customer customer;

    public ContactHistory(LocalDate date, String details, Customer customer) {
        this.date = date;
        this.details = details;
        this.customer = customer;
    }

    public ContactHistory(LocalDate date, String details) {
        this.date = date;
        this.details = details;
    }
}

package pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain;

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
    @OneToOne(mappedBy = "contactHistory")
    private Customer customer;

    public ContactHistory(LocalDate date, String details, Customer customer) {
        this.date = date;
        this.details = details;
        this.customer = customer;
    }
}

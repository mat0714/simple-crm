package pl.mateuszkolodziejczyk.simplecrm.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String details;
    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Event(LocalDate date, String details, Customer customer) {
        this.date = date;
        this.details = details;
        this.customer = customer;
    }

    public Event(LocalDate date, String details) {
        this.date = date;
        this.details = details;
    }
}

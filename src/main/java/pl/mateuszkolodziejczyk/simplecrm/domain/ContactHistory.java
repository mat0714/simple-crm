package pl.mateuszkolodziejczyk.simplecrm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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
    private LocalDate Date;
    private String details;
    @OneToOne(mappedBy = "contactHistory")
    private Customer customer;

}

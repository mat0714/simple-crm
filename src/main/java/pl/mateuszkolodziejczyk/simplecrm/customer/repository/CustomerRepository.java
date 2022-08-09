package pl.mateuszkolodziejczyk.simplecrm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

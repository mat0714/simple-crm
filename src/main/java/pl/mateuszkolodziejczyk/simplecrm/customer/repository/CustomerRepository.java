package pl.mateuszkolodziejczyk.simplecrm.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerBasicResponse;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select new pl.mateuszkolodziejczyk.simplecrm.customer.api.response.CustomerBasicResponse(" +
            "c.id as id, c.name, c.surname, c.phone, c.email, c.department, c.company) from customers c")
    Page<CustomerBasicResponse> findAllBy(PageRequest pageRequest);

    Boolean existsByNameAndSurname(String name, String surname);
}

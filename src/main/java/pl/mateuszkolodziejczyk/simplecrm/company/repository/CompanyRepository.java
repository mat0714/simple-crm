package pl.mateuszkolodziejczyk.simplecrm.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

package pl.mateuszkolodziejczyk.simplecrm.contacthistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain.ContactHistory;

@Repository
public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {
}
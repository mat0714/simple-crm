package pl.mateuszkolodziejczyk.simplecrm.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
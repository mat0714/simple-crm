package pl.mateuszkolodziejczyk.simplecrm.event.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.event.api.request.EventRequest;
import pl.mateuszkolodziejczyk.simplecrm.event.api.response.EventResponse;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import pl.mateuszkolodziejczyk.simplecrm.event.mapper.EventMapper;
import pl.mateuszkolodziejczyk.simplecrm.event.repository.EventRepository;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.customer.repository.CustomerRepository;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CustomerRepository customerRepository;

    public Long saveEvent(Long customerId, EventRequest eventRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.customerNotFound(customerId));
        Event event = eventRepository.save(eventMapper.toEvent(customer, eventRequest));
        return event.getId();
    }

    public void updateEvent(Long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id).orElseThrow(
                ExceptionSupplier.eventNotFound(id));
        eventRepository.save(eventMapper.toEvent(event, eventRequest));
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(ExceptionSupplier.eventNotFound(id));
        eventRepository.delete(event);
    }
}
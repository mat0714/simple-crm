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

    public EventResponse saveEvent(Long customerId, EventRequest eventRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ExceptionSupplier.EventNotFound(customerId));
        Event event = eventRepository.save(eventMapper.toEvent(customer, eventRequest));
        return eventMapper.toEventResponse(event);
    }

    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id).orElseThrow(
                ExceptionSupplier.EventNotFound(id));
        eventRepository.save(eventMapper.toEvent(event, eventRequest));
        return eventMapper.toEventResponse(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(ExceptionSupplier.EventNotFound(id));
        eventRepository.delete(event);
    }
}
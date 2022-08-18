package pl.mateuszkolodziejczyk.simplecrm.event.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.event.api.request.EventRequest;
import pl.mateuszkolodziejczyk.simplecrm.event.api.response.EventResponse;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;

@Component
public class EventMapper {

    public Event toEvent(Customer customer, EventRequest eventRequest) {
        return new Event(
                eventRequest.getDate(),
                eventRequest.getDetails(),
                customer
        );
    }

    public Event toEvent(Event event, EventRequest eventRequest) {
        event.setDate(eventRequest.getDate());
        event.setDetails(eventRequest.getDetails());
        return event;
    }

    public EventResponse toEventResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getDate(),
                event.getDetails(),
                event.getCustomer()
        );
    }
}
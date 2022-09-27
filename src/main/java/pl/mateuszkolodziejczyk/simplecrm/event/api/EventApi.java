package pl.mateuszkolodziejczyk.simplecrm.event.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.event.api.request.EventRequest;
import pl.mateuszkolodziejczyk.simplecrm.event.api.response.EventResponse;
import pl.mateuszkolodziejczyk.simplecrm.event.service.EventService;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventApi {

    private final EventService eventService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Long> saveEvent(
            @PathVariable Long customerId, @RequestBody EventRequest eventRequest) {
        Long id = eventService.saveEvent(customerId, eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Void> updateEvent(
            @PathVariable Long eventId, @RequestBody EventRequest eventRequest) {
        eventService.updateEvent(eventId, eventRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
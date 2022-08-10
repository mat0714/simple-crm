package pl.mateuszkolodziejczyk.simplecrm.contacthistory.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.request.ContactHistoryRequest;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.response.ContactHistoryResponse;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.service.ContactHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/contact-history")
@RequiredArgsConstructor
public class ContactHistoryApi {


    private final ContactHistoryService contactHistoryService;

    @PostMapping
    public ResponseEntity<ContactHistoryResponse> saveContactHistory(@RequestBody ContactHistoryRequest contactHistoryRequest) {
        ContactHistoryResponse contactHistoryResponse = contactHistoryService.saveContactHistory(contactHistoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactHistoryResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactHistoryResponse> findContactHistory(@PathVariable Long id) {
        ContactHistoryResponse contactHistoryResponse = contactHistoryService.findContactHistoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(contactHistoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<ContactHistoryResponse>> findAll() {
        List<ContactHistoryResponse> contactHistory = contactHistoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contactHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactHistoryResponse> updateContactHistory(
            @PathVariable Long id, @RequestBody ContactHistoryRequest contactHistoryRequest) {
        ContactHistoryResponse contactHistoryResponse = contactHistoryService.updateContactHistory(id, contactHistoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(contactHistoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactHistory(@PathVariable Long id) {
        contactHistoryService.deleteContactHistory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
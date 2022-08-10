package pl.mateuszkolodziejczyk.simplecrm.contacthistory.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.request.ContactHistoryRequest;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.response.ContactHistoryResponse;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain.ContactHistory;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.mapper.ContactHistoryMapper;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.repository.ContactHistoryRepository;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactHistoryService {

    private final ContactHistoryRepository contactHistoryRepository;
    private final ContactHistoryMapper contactHistoryMapper;

    public ContactHistoryResponse saveContactHistory(ContactHistoryRequest contactHistoryRequest) {
        ContactHistory contactHistory = contactHistoryRepository.save(contactHistoryMapper.toContactHistory(contactHistoryRequest));
        return contactHistoryMapper.toContactHistoryResponse(contactHistory);
    }

    public ContactHistoryResponse findContactHistoryById(Long id) {
        ContactHistory contactHistory = contactHistoryRepository.findById(id).orElseThrow(
                ExceptionSupplier.contactHistoryNotFound(id));
        return contactHistoryMapper.toContactHistoryResponse(contactHistory);
    }

    public List<ContactHistoryResponse> findAll() {
        return contactHistoryRepository.findAll().stream()
                .map(contactHistoryMapper::toContactHistoryResponse).collect(Collectors.toList());
    }

    public ContactHistoryResponse updateContactHistory(Long id, ContactHistoryRequest contactHistoryRequest) {
        ContactHistory contactHistory = contactHistoryRepository.findById(id).orElseThrow(
                ExceptionSupplier.contactHistoryNotFound(id));
        contactHistoryRepository.save(contactHistoryMapper.toContactHistory(contactHistory, contactHistoryRequest));
        return contactHistoryMapper.toContactHistoryResponse(contactHistory);
    }

    public void deleteContactHistory(Long id) {
        ContactHistory contactHistory = contactHistoryRepository.findById(id).orElseThrow(
                ExceptionSupplier.contactHistoryNotFound(id));
        contactHistoryRepository.delete(contactHistory);
    }
}
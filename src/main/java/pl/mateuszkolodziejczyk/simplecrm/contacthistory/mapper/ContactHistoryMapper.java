package pl.mateuszkolodziejczyk.simplecrm.contacthistory.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.request.ContactHistoryRequest;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.api.response.ContactHistoryResponse;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.domain.ContactHistory;

@Component
public class ContactHistoryMapper {

    public ContactHistory toContactHistory(ContactHistoryRequest contactHistoryRequest) {
        return new ContactHistory(
                contactHistoryRequest.getDate(),
                contactHistoryRequest.getDetails(),
                contactHistoryRequest.getCustomer()
        );
    }

    public ContactHistory toContactHistory(ContactHistory contactHistory, ContactHistoryRequest contactHistoryRequest) {
        contactHistory.setDate(contactHistoryRequest.getDate());
        contactHistory.setDetails(contactHistoryRequest.getDetails());
        contactHistory.setCustomer(contactHistoryRequest.getCustomer());

        return contactHistory;
    }

    public ContactHistoryResponse toContactHistoryResponse(ContactHistory contactHistory) {
        return new ContactHistoryResponse(
                contactHistory.getId(),
                contactHistory.getDate(),
                contactHistory.getDetails(),
                contactHistory.getCustomer()
        );
    }
}
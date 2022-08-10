package pl.mateuszkolodziejczyk.simplecrm.contacthistory.exception;

public class ContactHistoryNotFoundException extends RuntimeException{

    public ContactHistoryNotFoundException(Long id) {
        super(String.format("Contact History with id %d not found", id));
    }
}
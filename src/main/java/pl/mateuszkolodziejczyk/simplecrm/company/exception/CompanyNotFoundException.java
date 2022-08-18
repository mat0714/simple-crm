package pl.mateuszkolodziejczyk.simplecrm.company.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super(String.format("Company with id %d not found", id));
    }
}

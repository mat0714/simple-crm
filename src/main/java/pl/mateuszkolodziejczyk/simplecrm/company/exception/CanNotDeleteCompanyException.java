package pl.mateuszkolodziejczyk.simplecrm.company.exception;

public class CanNotDeleteCompanyException extends RuntimeException {

    public CanNotDeleteCompanyException() {
        super("Can't delete company which is assigned to existing customer");
    }
}

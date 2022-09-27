package pl.mateuszkolodziejczyk.simplecrm.customer.exception;

public class CanNotDeleteCustomerException extends RuntimeException {

    public CanNotDeleteCustomerException() {
        super("Can't delete customer which is assigned to company, event or employee");
    }
}

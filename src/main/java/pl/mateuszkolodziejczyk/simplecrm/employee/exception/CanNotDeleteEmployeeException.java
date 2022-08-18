package pl.mateuszkolodziejczyk.simplecrm.employee.exception;

public class CanNotDeleteEmployeeException extends RuntimeException{

    public CanNotDeleteEmployeeException() {
        super("Can't delete employee which is assigned to existing customer");
    }
}

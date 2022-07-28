package pl.mateuszkolodziejczyk.simplecrm.support.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super(String.format("Employee with id %d not found", id));
    }

}

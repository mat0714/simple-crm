package pl.mateuszkolodziejczyk.simplecrm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mateuszkolodziejczyk.simplecrm.company.exception.CanNotDeleteCompanyException;
import pl.mateuszkolodziejczyk.simplecrm.employee.exception.CanNotDeleteEmployeeException;
import pl.mateuszkolodziejczyk.simplecrm.event.exception.EventNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.customer.exception.CustomerNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.employee.exception.EmployeeNotFoundException;

@ControllerAdvice
public class ExceptionAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvisor.class);

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse customerNotFound(CustomerNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse employeeNotFound(EmployeeNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse eventNotFound(EventNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CanNotDeleteCompanyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageResponse canNotDeleteCompanyException(CanNotDeleteCompanyException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CanNotDeleteEmployeeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageResponse canNotDeleteEmployeeException(CanNotDeleteEmployeeException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }
}

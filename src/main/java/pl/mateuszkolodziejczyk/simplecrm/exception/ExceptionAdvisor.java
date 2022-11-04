package pl.mateuszkolodziejczyk.simplecrm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mateuszkolodziejczyk.simplecrm.company.exception.CanNotDeleteCompanyException;
import pl.mateuszkolodziejczyk.simplecrm.company.exception.CompanyNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.customer.exception.CanNotDeleteCustomerException;
import pl.mateuszkolodziejczyk.simplecrm.employee.exception.CanNotDeleteEmployeeException;
import pl.mateuszkolodziejczyk.simplecrm.event.exception.EventNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.customer.exception.CustomerNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.employee.exception.EmployeeNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.user.exception.UserNotFoundException;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse companyNotFound(CompanyNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse customerNotFound(CustomerNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse employeeNotFound(EmployeeNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse eventNotFound(EventNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse userNotFound(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CanNotDeleteCompanyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageResponse canNotDeleteCompanyException(CanNotDeleteCompanyException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CanNotDeleteCustomerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageResponse canNotDeleteCustomerException(CanNotDeleteCustomerException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(CanNotDeleteEmployeeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageResponse canNotDeleteEmployeeException(CanNotDeleteEmployeeException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }
}

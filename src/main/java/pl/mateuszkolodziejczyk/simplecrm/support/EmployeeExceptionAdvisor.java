package pl.mateuszkolodziejczyk.simplecrm.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mateuszkolodziejczyk.simplecrm.shared.api.response.ErrorMessageResponse;
import pl.mateuszkolodziejczyk.simplecrm.support.exception.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeExceptionAdvisor.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse resourceNotFound(EmployeeNotFoundException exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }
}

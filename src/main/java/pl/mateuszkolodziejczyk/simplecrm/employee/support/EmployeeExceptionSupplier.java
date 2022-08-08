package pl.mateuszkolodziejczyk.simplecrm.employee.support;

import pl.mateuszkolodziejczyk.simplecrm.employee.support.exception.EmployeeNotFoundException;

import java.util.function.Supplier;

public class EmployeeExceptionSupplier {

    public static Supplier<EmployeeNotFoundException> itemNotFound(Long id) {
        return () -> new EmployeeNotFoundException(id);
    }
}

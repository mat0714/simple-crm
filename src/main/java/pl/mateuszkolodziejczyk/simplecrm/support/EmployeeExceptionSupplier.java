package pl.mateuszkolodziejczyk.simplecrm.support;

import pl.mateuszkolodziejczyk.simplecrm.support.exception.EmployeeNotFoundException;

import java.util.function.Supplier;

public class EmployeeExceptionSupplier {

    public static Supplier<EmployeeNotFoundException> itemNotFound(Long id) {
        return () -> new EmployeeNotFoundException(id);
    }
}

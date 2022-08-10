package pl.mateuszkolodziejczyk.simplecrm.exception;

import pl.mateuszkolodziejczyk.simplecrm.company.exception.CompanyNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.contacthistory.exception.ContactHistoryNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.customer.exception.CustomerNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.employee.exception.EmployeeNotFoundException;

import java.util.function.Supplier;

public class ExceptionSupplier {

    public static Supplier<EmployeeNotFoundException> employeeNotFound(Long id) {
        return () -> new EmployeeNotFoundException(id);
    }

    public static Supplier<CustomerNotFoundException> customerNotFound(Long id) {
        return () -> new CustomerNotFoundException(id);
    }

    public static Supplier<CompanyNotFoundException> companyNotFound(Long id) {
        return () -> new CompanyNotFoundException(id);
    }

    public static Supplier<ContactHistoryNotFoundException> contactHistoryNotFound(Long id) {
        return () -> new ContactHistoryNotFoundException(id);
    }

}

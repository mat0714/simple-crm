package pl.mateuszkolodziejczyk.simplecrm.company.mapper;

import org.springframework.stereotype.Component;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.api.response.CompanyResponse;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;

@Component
public class CompanyMapper {

    public Company toCompany(CompanyRequest companyRequest) {
        return new Company(
                companyRequest.getName(),
                companyRequest.getStreetNumber(),
                companyRequest.getStreetName(),
                companyRequest.getCity(),
                companyRequest.getVoivodeship(),
                companyRequest.getZipCode()
        );
    }

    public Company toCompany(Company company, CompanyRequest companyRequest) {
        company.setName(companyRequest.getName());
        company.setStreetNumber(companyRequest.getStreetNumber());
        company.setStreetName(companyRequest.getStreetName());
        company.setCity(companyRequest.getCity());
        company.setVoivodeship(companyRequest.getVoivodeship());
        company.setZipCode(companyRequest.getZipCode());
        return company;
    }

    public CompanyResponse toCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getStreetNumber(),
                company.getStreetName(),
                company.getCity(),
                company.getVoivodeship(),
                company.getZipCode(),
                company.getCustomer()
        );
    }
}

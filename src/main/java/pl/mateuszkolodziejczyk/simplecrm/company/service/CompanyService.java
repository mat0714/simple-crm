package pl.mateuszkolodziejczyk.simplecrm.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.api.response.CompanyResponse;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.company.mapper.CompanyMapper;
import pl.mateuszkolodziejczyk.simplecrm.company.repository.CompanyRepository;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = companyRepository.save(companyMapper.toCompany(companyRequest));
        return companyMapper.toCompanyResponse(company);
    }

    public CompanyResponse findCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                ExceptionSupplier.companyNotFound(id));
        return companyMapper.toCompanyResponse(company);
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toCompanyResponse).collect(Collectors.toList());
    }

    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow(
                ExceptionSupplier.companyNotFound(id));
        companyRepository.save(companyMapper.toCompany(company, companyRequest));
        return companyMapper.toCompanyResponse(company);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                ExceptionSupplier.companyNotFound(id));
        companyRepository.delete(company);
    }
}

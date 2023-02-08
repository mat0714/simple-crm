package pl.mateuszkolodziejczyk.simplecrm.company.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.api.response.CompanyResponse;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.company.mapper.CompanyMapper;
import pl.mateuszkolodziejczyk.simplecrm.company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMapper companyMapper;
    @InjectMocks
    private CompanyService companyService;

    private CompanyRequest companyRequest;
    private Company company;
    private CompanyResponse companyResponse;

    @BeforeEach
    void setUp() {
        companyRequest = new CompanyRequest(
                "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017");
        company = new Company(1L, "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017", null);
    }

    @Test
    void ShouldSaveCompany() {
        BDDMockito.given(companyRepository.save(companyMapper.toCompany(companyRequest))).willReturn(company);
        Assertions.assertThat(companyService.saveCompany(companyRequest)).isEqualTo(1L);
    }

    @Test
    void shouldFindCompanyById() {
        BDDMockito.given(companyRepository.findById(1L)).willReturn(Optional.of(company));
        BDDMockito.given(companyMapper.toCompanyResponse(company)).willReturn(companyResponse);

        Assertions.assertThat(companyService.findCompanyById(1L)).isEqualTo(companyResponse);
    }

    @Test
    void ShouldFindAllCompanies() {
        List<Company> companies = List.of(company);
        BDDMockito.given(companyRepository.findAll()).willReturn(companies);
        companyService.findAll();

        Mockito.verify(companyMapper, Mockito.times(1))
                .toCompanyResponse(company);
    }

    @Test
    void shouldUpdateCompany() {
        BDDMockito.given(companyRepository.findById(1L)).willReturn(Optional.of(company));
        BDDMockito.given(companyMapper.toCompany(company, companyRequest)).willReturn(company);
        companyService.updateCompany(1L, companyRequest);

        Mockito.verify(companyRepository, Mockito.times(1))
                .save(companyMapper.toCompany(company, companyRequest));
    }

    @Test
    void shouldDeleteCompany() {
        BDDMockito.given(companyRepository.findById(1L)).willReturn(Optional.of(company));
        companyService.deleteCompany(1L);

        Mockito.verify(companyRepository, Mockito.times(1))
                .delete(company);
    }
}
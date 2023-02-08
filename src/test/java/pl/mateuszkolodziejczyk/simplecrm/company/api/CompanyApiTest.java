package pl.mateuszkolodziejczyk.simplecrm.company.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.api.response.CompanyResponse;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.company.exception.CompanyNotFoundException;
import pl.mateuszkolodziejczyk.simplecrm.company.service.CompanyService;
import java.util.List;

import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = CompanyApi.class,
        excludeAutoConfiguration = {
                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
        })

class CompanyApiTest {

    @MockBean
    private CompanyService companyService;
    @Autowired
    private MockMvc mockMvc;

    private static CompanyRequest companyRequest;
    private static String companyRequestJson;
    private static CompanyResponse companyResponse;
    private static CompanyResponse secondCompanyResponse;

    @BeforeAll
    static void setup() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        companyRequest = new CompanyRequest(
                "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017");
        companyRequestJson = objectMapper.writeValueAsString(companyRequest);
        companyResponse = new CompanyResponse(1L, "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017", null);
        secondCompanyResponse = new CompanyResponse(2L, "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017", null);
    }

    @Test
    void shouldSaveCompany() throws Exception {
        BDDMockito.given(companyService.saveCompany(companyRequest)).willReturn(3L);

        mockMvc.perform(post("/api/companies")
                        .content(companyRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(companyService, Mockito.times(1)).saveCompany(Mockito.any());
    }

    @Test
    void shouldFindCompany() throws Exception {
        BDDMockito.given(companyService.findCompanyById(1L)).willReturn(companyResponse);

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Company sp. z o.o.")))
                .andExpect(jsonPath("$.streetNumber", Matchers.is("60")))
                .andExpect(jsonPath("$.streetName", Matchers.is("Krakowska")))
                .andExpect(jsonPath("$.city", Matchers.is("Gdańsk")))
                .andExpect(jsonPath("$.voivodeship", Matchers.is("Pomorskie")))
                .andExpect(jsonPath("$.zipCode", Matchers.is("80-017")))
                .andExpect(jsonPath("$.customer", Matchers.nullValue()));
    }

    @Test
    void shouldNotFindCompanyWhenCompanyDoesNotExist() throws Exception {
        BDDMockito.given(companyService.findCompanyById(100L)).willThrow(new CompanyNotFoundException(100L));

        mockMvc.perform(get("/api/companies/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("Company with id 100 not found")));
    }

    @Test
    void shouldFindAllCompanies() throws Exception {
        List<CompanyResponse> companies = List.of(companyResponse, secondCompanyResponse);
        BDDMockito.given(companyService.findAll()).willReturn(companies);

        MvcResult mvcResult = mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andReturn();

        String companiesResultJson = mvcResult.getResponse().getContentAsString();
        List<Company> companiesResult = new ObjectMapper()
                .readValue(companiesResultJson, new TypeReference<>() {});

        Assertions.assertThat(companiesResult).hasSize(2);
    }

    @Test
    void shouldUpdateCompany() throws Exception {
        mockMvc.perform(put("/api/companies/1")
                        .content(companyRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(companyService, Mockito.times(1))
                .updateCompany(Mockito.eq(1L), Mockito.any());
    }

    @Test
    void shouldNotUpdateCompanyWhenCompanyDoesNotExist() throws Exception {
        willThrow(new CompanyNotFoundException(100L))
                .given(companyService).updateCompany(Mockito.eq(100L), Mockito.any());

        mockMvc.perform(put("/api/companies/100")
                        .content(companyRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("Company with id 100 not found")));

        Mockito.verify(companyService, Mockito.times(1))
                .updateCompany(Mockito.eq(100L), Mockito.any());
    }

    @Test
    void shouldDeleteCompany() throws Exception {
        mockMvc.perform(delete("/api/companies/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(companyService, Mockito.times(1))
                .deleteCompany(1L);
    }

    @Test
    void shouldNotDeleteCompanyWhenCompanyDoesNotExist() throws Exception {
        willThrow(new CompanyNotFoundException(100L))
                .given(companyService).deleteCompany(100L);

        mockMvc.perform(delete("/api/companies/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("Company with id 100 not found")));

        Mockito.verify(companyService, Mockito.times(1))
                .deleteCompany(100L);
    }
}
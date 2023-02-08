package pl.mateuszkolodziejczyk.simplecrm.company.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.company.repository.CompanyRepository;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
class CompanyApiIntegrationTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${local.server.port}")
    private int port;
    private final HttpHeaders authorizationHeader = new HttpHeaders();
    private String token;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // @formatter:off
        token = given()
                    .body("{\"username\": \"Manager\", \"password\": \"Password123\"}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/api/login")
                .andReturn().getHeader(HttpHeaders.AUTHORIZATION);
        // @formatter:on

        log.debug("Generated token: {}", token);
    }

    @Test
    void shouldSaveCompany() throws Exception {
        CompanyRequest companyRequest = new CompanyRequest(
                "Company sp. z o.o.", "60", "Krakowska", "Gdańsk",
                "Pomorskie", "80-017");
        ObjectMapper objectMapper = new ObjectMapper();
        String companyRequestJson = objectMapper.writeValueAsString(companyRequest);

        authorizationHeader.set(HttpHeaders.AUTHORIZATION, token);

        // @formatter:off
        RestAssured
                .given()
                    .body(companyRequestJson)
                    .headers(authorizationHeader)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/api/companies")
                .then()
                    .statusCode(201)
                    .body(Matchers.notNullValue());
        // @formatter:on
    }

    @Test
    void shouldFindCompany() {
        authorizationHeader.set(HttpHeaders.AUTHORIZATION, token);

        // @formatter:off
        RestAssured
                .given()
                    .pathParam("id", 1)
                    .headers(authorizationHeader)
                .when()
                    .get("/api/companies/{id}")
                .then()
                    .statusCode(200)
                    .body("id", Matchers.equalTo(1))
                    .body("name", Matchers.equalTo("E CORP"))
                    .body("streetNumber", Matchers.equalTo("5"))
                    .body("streetName", Matchers.equalTo("Domaniewska"))
                    .body("city", Matchers.equalTo("Warsaw"))
                    .body("voivodeship", Matchers.equalTo("Mazowieckie"))
                    .body("zipCode", Matchers.equalTo("02-672"));
        // @formatter:on
    }

    @Test
    void shouldFindAllCompanies() {
        authorizationHeader.set(HttpHeaders.AUTHORIZATION, token);

        // @formatter:off
        RestAssured
                .given()
                    .headers(authorizationHeader)
                .when()
                    .get("/api/companies")
                .then()
                    .statusCode(200)
                    .assertThat().body("list.size()", Matchers.equalTo(2));
        // @formatter:on
    }

    @Test
    @DirtiesContext
    void shouldUpdateCompany() throws JsonProcessingException {
        authorizationHeader.set(HttpHeaders.AUTHORIZATION, token);

        CompanyRequest companyRequest = new CompanyRequest(
                "ADT sp.z o.o.", "60A", "Lipowa", "Gdańsk", "Pomorskie", "80-017");

        ObjectMapper objectMapper = new ObjectMapper();
        String companyRequestJson = objectMapper.writeValueAsString(companyRequest);

        // @formatter:off
        RestAssured
                .given()
                    .body(companyRequestJson)
                    .contentType(ContentType.JSON)
                    .pathParam("id", 1)
                    .headers(authorizationHeader)
                .when()
                    .put("/api/companies/{id}")
                .then()
                    .statusCode(200);
        // @formatter:on
    }

    @Test
    void shouldDeleteCompany() {
        Company company = new Company("X Technologies", "2B", "Bracka", "Lublin",
                "Lubelskie", "20-007");
        Company savedCompany = companyRepository.save(company);
        Long id = savedCompany.getId();

        authorizationHeader.set(HttpHeaders.AUTHORIZATION, token);

        // @formatter:off
        RestAssured
                .given()
                    .pathParam("id", id)
                    .headers(authorizationHeader)
                .when()
                    .delete("/api/companies/{id}")
                .then()
                    .statusCode(204);
        // @formatter:on
    }
}

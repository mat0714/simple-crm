package pl.mateuszkolodziejczyk.simplecrm.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldLoginAndGetToken() throws Exception {
        // Given
        MockHttpServletRequestBuilder request = post("/api/login")
                .content("{\"username\": \"Manager\", \"password\": \"Password123\"}")
                .contentType(MediaType.APPLICATION_JSON);

        // When
        MvcResult login = mockMvc.perform(request).andReturn();

        // Then
        int status = login.getResponse().getStatus();
        String authorizationHeader = login.getResponse().getHeader(HttpHeaders.AUTHORIZATION);

        assertThat(status).isEqualTo(200);
        assertThat(authorizationHeader).isNotEmpty();
        assertThat(authorizationHeader).startsWith("Bearer ");
        assertThat(authorizationHeader).hasSizeGreaterThan(20);
    }

    @Test
    void shouldNotLogin() throws Exception {
        // Given
        MockHttpServletRequestBuilder request = post("/api/login")
                .content("{\"username\": \"Manager\", \"password\": \"WrongPassword\"}")
                .contentType(MediaType.APPLICATION_JSON);

        // When
        MvcResult login = mockMvc.perform(request).andReturn();

        // Then
        int status = login.getResponse().getStatus();
        String authorizationHeader = login.getResponse().getHeader(HttpHeaders.AUTHORIZATION);

        assertThat(status).isEqualTo(401);
        assertThat(authorizationHeader).isNull();
    }
}
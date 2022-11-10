package pl.mateuszkolodziejczyk.simplecrm.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mateuszkolodziejczyk.simplecrm.company.domain.Company;
import pl.mateuszkolodziejczyk.simplecrm.customer.domain.Customer;
import pl.mateuszkolodziejczyk.simplecrm.employee.domain.Employee;
import pl.mateuszkolodziejczyk.simplecrm.event.domain.Event;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .apiInfo(apiInfo())
                .ignoredParameterTypes(Company.class, Customer.class, Employee.class, Event.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Simple-CRM API Documentation").version("1.0.0")
                .description("This is basic API documentation to Simple-CRM. " +
                        "Github: https://github.com/mat0714/simple-crm")
                .build();
    }

    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationContext -> true)
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}

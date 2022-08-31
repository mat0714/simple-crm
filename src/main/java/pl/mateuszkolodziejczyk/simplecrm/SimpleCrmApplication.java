package pl.mateuszkolodziejczyk.simplecrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SimpleCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrmApplication.class, args);
	}

}

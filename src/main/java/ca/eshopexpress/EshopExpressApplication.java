package ca.eshopexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EshopExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopExpressApplication.class, args);
	}
}
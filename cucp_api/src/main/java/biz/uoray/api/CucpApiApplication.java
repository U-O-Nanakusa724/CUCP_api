package biz.uoray.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "biz.uoray.common")
public class CucpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucpApiApplication.class, args);
	}

}

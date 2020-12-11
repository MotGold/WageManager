package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.web.SessionExpirationChecker;

@SpringBootApplication
public class WagesApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext boot = SpringApplication.run(WagesApplication.class, args);

		 SessionExpirationChecker sec = boot.getBean(SessionExpirationChecker.class);
		 sec.start();
		// sec.terminate();

	}
}
package app.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	
	@Bean
	public Map<String, Session> sessionsMap(){
		return new HashMap<String, Session>();
	}

}

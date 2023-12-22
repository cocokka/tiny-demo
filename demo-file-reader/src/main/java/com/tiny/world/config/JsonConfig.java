package com.tiny.world.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

	@Bean
	public JsonMapper jsonMapper() {
		return new JsonMapper();
	}

}

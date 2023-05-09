package com.atilaacedo.webclientfootball.WebClienteFootball.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FootballWebClientConfiguration {
	@Bean
	public WebClient webclient() {
		WebClient webClient = WebClient.builder()
				.baseUrl("https://v3.football.api-sports.io")
				.build();
		return webClient;
	}
}

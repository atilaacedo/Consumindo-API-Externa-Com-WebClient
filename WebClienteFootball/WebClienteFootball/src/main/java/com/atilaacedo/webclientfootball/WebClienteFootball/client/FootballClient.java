package com.atilaacedo.webclientfootball.WebClienteFootball.client;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atilaacedo.webclientfootball.WebClienteFootball.response.TeamResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FootballClient {
	
	@Autowired
	WebClient webClient;


	
	
	public Mono<TeamResponse> findAnTeamById(String id){
		log.info("Buscando time por ID[{}]", id);
		
		return webClient
				.get()
				.uri("/teams?id=" + id)
				.header("X-RapidAPI-Key", "Sua-KEY")
				.retrieve()
				.bodyToMono(TeamResponse.class);
	}
	
	
}

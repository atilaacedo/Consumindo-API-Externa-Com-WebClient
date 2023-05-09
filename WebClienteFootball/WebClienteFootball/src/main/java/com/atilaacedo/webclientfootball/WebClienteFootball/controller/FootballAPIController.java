package com.atilaacedo.webclientfootball.WebClienteFootball.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atilaacedo.webclientfootball.WebClienteFootball.client.FootballClient;
import com.atilaacedo.webclientfootball.WebClienteFootball.response.TeamResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclientfootball")
public class FootballAPIController {
	
	FootballClient footballClient;
	
	@GetMapping("/teams/{id}")
	public Mono<TeamResponse> getTeamById(@PathVariable String id){
		return footballClient.findAnTeamById(id);
	}
}

package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RescueTeamClient {

    @Autowired
    private WebClient webClient;

    @Bean
    public Flux<RescueTeamDto> getRescueTeam() {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/rescueTeam")
                .retrieve()
                .bodyToFlux(RescueTeamDto.class);
    }
}

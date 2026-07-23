package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.rescueTeamDto.RescueTeamDto;
import com.disaster.missionservice.exception.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RescueTeamClient {

    @Autowired
    private WebClient webClient;

    public Flux<RescueTeamDto> getRescueTeam() {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/rescueTeam")
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new TeamNotFoundException("Rescue teams not found in rescue service")))
                .bodyToFlux(RescueTeamDto.class);
    }

    public Mono<RescueTeamDto> getRescueTeamById(Long teamId) {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/rescueTeam/{id}", teamId)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new TeamNotFoundException("Rescue team not found in rescue service")))
                .bodyToMono(RescueTeamDto.class);
    }

    public Mono<Void> updateTeamStatus(Long teamId, RescueTeamDto rescueTeamDto) {
        return webClient.put()
                .uri("http://localhost:8082/api/v1/rescueTeam/{id}", teamId)
                .bodyValue(rescueTeamDto)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new TeamNotFoundException("Rescue team not found in rescue service")))
                .bodyToMono(Void.class);
    }
}

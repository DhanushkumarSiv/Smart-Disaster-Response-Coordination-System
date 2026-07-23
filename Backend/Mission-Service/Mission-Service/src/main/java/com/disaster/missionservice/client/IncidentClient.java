package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.incidentDto.IncidentDto;
import com.disaster.missionservice.exception.IncidentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IncidentClient {

    @Autowired
    private WebClient webClient;

    public Flux<IncidentDto> getIncident() {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/incident")
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new IncidentNotFoundException("Incidents not found in incident service")))
                .bodyToFlux(IncidentDto.class);
    }

    public Mono<IncidentDto> getIncidentById(Long incidentId) {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/incident/{id}", incidentId)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new IncidentNotFoundException("Incident not found in incident service")))
                .bodyToMono(IncidentDto.class);
    }

    public Mono<Void> updateIncident(Long incidentId, IncidentDto incidentDto) {
        return webClient.put()
                .uri("http://localhost:8081/api/v1/incident/{id}", incidentId)
                .bodyValue(incidentDto)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new IncidentNotFoundException("Incident not found in incident service")))
                .bodyToMono(Void.class);
    }
}

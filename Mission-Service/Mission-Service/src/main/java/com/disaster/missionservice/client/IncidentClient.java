package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.incidentDto.IncidentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class IncidentClient {

    @Autowired
    private WebClient webClient;

    public Flux<IncidentDto> getIncident() {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/incident")
                .retrieve()
                .bodyToFlux(IncidentDto.class);
    }

}

package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.resourceDto.ResourceDto;
import com.disaster.missionservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResourceClient {

    @Autowired
    private WebClient webClient;

    public Flux<ResourceDto> getResources() {
        return webClient.get()
                .uri("http://localhost:8083/api/v1/resource")
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new ResourceNotFoundException("Resources not found in resource service")))
                .bodyToFlux(ResourceDto.class);
    }
}

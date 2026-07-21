package com.disaster.missionservice.client;

import com.disaster.missionservice.dto.alertDto.AlertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AlertClient {

    @Autowired
    private WebClient webClient;

    public void sendAlert(AlertDto alertDto) {

        webClient.post()
                .uri("http://localhost:8084/api/v1/alert")
                .bodyValue(alertDto)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}

package com.disaster.missionservice.exception;

public class NoAvailableTeamException extends RuntimeException {
    public NoAvailableTeamException(String message) {
        super(message);
    }
}

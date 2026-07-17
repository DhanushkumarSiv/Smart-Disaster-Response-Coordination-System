package com.disaster.missionservice.mapper;

import com.disaster.missionservice.dto.incidentDto.IncidentDisaster;
import com.disaster.missionservice.dto.rescueTeamDto.TeamDepartment;
import com.disaster.missionservice.exception.TeamNotFoundException;

public class DisasterMapper {

    public TeamDepartment mapDepartment(IncidentDisaster incidentDisaster){

        switch(incidentDisaster){
            case FIRE:
                return TeamDepartment.FIRE;

            case FLOOD:
                return TeamDepartment.FLOOD;

            case CYCLONE:
                return TeamDepartment.CYCLONE;

            case EARTHQUAKE:
                return TeamDepartment.EARTHQUAKE;

            case TSUNAMI:
                return TeamDepartment.TSUNAMI;

                default:
                    throw new TeamNotFoundException("Team Not Found.");
        }
    }
}

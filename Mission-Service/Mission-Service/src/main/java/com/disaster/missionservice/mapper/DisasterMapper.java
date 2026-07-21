package com.disaster.missionservice.mapper;

import com.disaster.missionservice.dto.incidentDto.DisasterName;
import com.disaster.missionservice.dto.rescueTeamDto.TeamDepartment;
import com.disaster.missionservice.exception.TeamNotFoundException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class DisasterMapper {

    public TeamDepartment mapDepartment(DisasterName incidentDisaster){

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

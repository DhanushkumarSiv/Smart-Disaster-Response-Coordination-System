package com.disaster.missionservice.mapper;

import com.disaster.missionservice.entity.DisasterName;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public class ResourceMapper {

    public String resourceMapping(DisasterName disasterName) {

        switch(disasterName) {

            case FIRE :
                return "FIRE_EXTINGUISHER";

            case FLOOD :
                return "BOAT";

            case CYCLONE :
                return "LIFE_JACKET";

                case EARTHQUAKE :
                    return "FIRST_AID_KIT";

            default :
                return "DRINKING_WATER";

        }
    }
}

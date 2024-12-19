package com.example.Rocket.mapper;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.entity.Rocket;


public class RocketMapper {
    // With mapper we convert rocket jpa into rocketDto we use entity values because entity is the one that is connected to the database and
    // dto Data Transfer Object is transfer data from layer to layer.
    public static RocketDto mapToRocketDto(Rocket rocket) {
        return new RocketDto(rocket.getId(),
                rocket.getName(),
                rocket.getManufacturer(),
                rocket.getHeight(),
                rocket.getWeight(),
                rocket.getMissionType(),
                rocket.getPartsCount()
                //rocket.getVersion()
//                rocket.getImageUrl(),
//                rocket.getParts()
        );
    }
    //Map RocketDto to Rocket Jpa
    public static Rocket mapToRocket(RocketDto rocketDto) {
        return new Rocket(rocketDto.getId(),
                rocketDto.getName(),
                rocketDto.getManufacturer(),
                rocketDto.getHeight(),
                rocketDto.getWeight(),
                rocketDto.getMissionType(),
                rocketDto.getPartsCount()
//                rocketDto.getVersion()
//                rocketDto.getImageUrl(),
//                rocketDto.getParts()
        );
    }
}

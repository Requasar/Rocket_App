package com.example.Rocket.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RocketDto {
    private Long id;
    private String name;
    private String manufacturer;
    private double height;
    private double weight;
    private String missionType;
    private int partsCount;
    //private Long version;
//    private String imageUrl;
//    private List<String> parts;
}

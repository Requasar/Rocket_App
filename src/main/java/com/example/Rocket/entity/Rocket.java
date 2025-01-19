package com.example.Rocket.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rocket {
    private Long id;
    private String name;
    private String manufacturer;
    private String height;
    private String weight;
    private String missionType;
    private String partsCount;
 //   private Long version;
//    private String imageUrl;
//    private List<String> parts;
}
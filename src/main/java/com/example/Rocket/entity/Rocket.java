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
    private double height;
    private double weight;
    private String missionType;
    private int partsCount;
 //   private Long version;
//    private String imageUrl;
//    private List<String> parts;
}
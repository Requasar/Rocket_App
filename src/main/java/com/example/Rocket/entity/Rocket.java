package com.example.Rocket.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity //to match our class with database
@Table(name = "RocketV3") // name of the database structure
public class Rocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;


    private double height;


    private double weight;

    @Column(name = "missionType")
    private String missionType;


    private int partsCount;
    @Version  // <-- Optimistic Locking annotation
    private Long version;
}

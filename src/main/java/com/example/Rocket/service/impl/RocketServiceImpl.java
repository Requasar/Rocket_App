package com.example.Rocket.service.impl;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.entity.Rocket;
import com.example.Rocket.exception.ResourceNotFoundException;
import com.example.Rocket.mapper.RocketMapper;
import com.example.Rocket.repository.RocketRepository;
import com.example.Rocket.service.RocketService;
import lombok.AllArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//impl is created to differ service interface and service implementation

@Service //spring container to create a spring bean for RocketServiceImpl class
@AllArgsConstructor //constructor with all arguments
public class RocketServiceImpl implements RocketService {

    //injecting pre dependencies for construct we use
    private RocketRepository rocketRepository;

    //before adding create Rocket we should convert RocketDto to Rocket Entity
    //And we need to store Rocket Entity into the database
    @Override
    @Transactional
    public RocketDto createRocket(RocketDto rocketDto) {
        try {
            Rocket rocket = RocketMapper.mapToRocket(rocketDto);
            Rocket savedRocket = rocketRepository.save(rocket);
            return RocketMapper.mapToRocketDto(savedRocket);
        } catch (StaleObjectStateException e) {
            // Hata oluşursa, kullanıcıya anlamlı bir mesaj göster
            throw new RuntimeException("This rocket record has been modified by another transaction. Please try again.");
        }
    }

    @Override
    public RocketDto getRocketById(Long rocketId) {
        Rocket rocket = rocketRepository.findById(rocketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rocket is not exist in given id: " + rocketId));
        return RocketMapper.mapToRocketDto(rocket);
    }

    @Override
    public List<RocketDto> getAllRockets() {
        List<Rocket> rockets = rocketRepository.findAll();
        return rockets.stream().map((rocket) -> RocketMapper.mapToRocketDto(rocket))
                .collect(Collectors.toList());
    }

    @Override
    public RocketDto updateRocket(Long rocketId, RocketDto updatedRocket) {
        Rocket rocket = rocketRepository.findById(rocketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rocket is not exist in given id: " + rocketId));
        rocket.setName(updatedRocket.getName());
        rocket.setManufacturer(updatedRocket.getManufacturer());
        rocket.setHeight(updatedRocket.getHeight());
        rocket.setWeight(updatedRocket.getWeight());
        rocket.setMissionType(updatedRocket.getMissionType());
        rocket.setPartsCount(updatedRocket.getPartsCount());
        try {
            Rocket savedRocket = rocketRepository.save(rocket);
            return RocketMapper.mapToRocketDto(savedRocket);
        } catch (StaleObjectStateException e) {
            throw new RuntimeException("This rocket record has been modified by another transaction. Please try again.");
        }

    }

    @Override
    public void deleteRocket(Long rocketId) {
        Rocket rocket = rocketRepository.findById(rocketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rocket is not exist in given id: " + rocketId));
        rocketRepository.deleteById(rocketId);
    }
}
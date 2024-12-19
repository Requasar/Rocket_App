package com.example.Rocket.service;

import com.example.Rocket.dto.RocketDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RocketService {

    RocketDto createRocket(RocketDto rocketDto);

    RocketDto getRocketById(Long rocketId);

    List<RocketDto> getAllRockets();

    RocketDto updateRocket(Long rocketId, RocketDto updatedRocket);

    void deleteRocket(Long rocketId);

//    RocketDto uploadRocketImage(Long rocketId, MultipartFile image) throws IOException;
}
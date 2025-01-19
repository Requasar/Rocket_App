package com.example.Rocket.service.impl;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.entity.Rocket;
import com.example.Rocket.exception.ResourceNotFoundException;
import com.example.Rocket.mapper.RocketMapper;
import com.example.Rocket.repository.RocketRepository;
import com.example.Rocket.service.RocketService;
import lombok.AllArgsConstructor;
//import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//impl is created to differ service interface and service implementation

@Service //spring container to create a spring bean for RocketServiceImpl class
@AllArgsConstructor //constructor with all arguments
public class RocketServiceImpl implements RocketService {


    private static final Logger log = LoggerFactory.getLogger(RocketServiceImpl.class);


    //    @Value("${upload.directory}")
//    private String uploadDir;
    //injecting pre dependencies for construct we use
    private final RocketRepository rocketRepository;

    @Override
    public RocketDto createRocket(RocketDto rocketDto) {
        Rocket rocket = RocketMapper.mapToRocket(rocketDto);
        rocketRepository.create(rocket);
        RocketDto createdRocketDto = RocketMapper.mapToRocketDto(rocket);
        return createdRocketDto;
    }

    @Override
    public RocketDto getRocketById(Long rocketId) {
        Optional<Rocket> rocketOptional = rocketRepository.findById(rocketId);
        return rocketOptional
                .map(RocketMapper::mapToRocketDto)
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + rocketId));
    }

    @Override
    public List<RocketDto> getAllRockets() {
        List<Rocket> rockets = rocketRepository.findAll();
        return rockets.stream()
                .map(RocketMapper::mapToRocketDto)
                .collect(Collectors.toList());
    }

    @Override
    public RocketDto updateRocket(Long rocketId, RocketDto updatedRocket) {
        Optional<Rocket> existingRocketOptional = rocketRepository.findById(rocketId);
        Rocket existingRocket = existingRocketOptional
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + rocketId));
        Rocket updatedRocketEntity = RocketMapper.mapToRocket(updatedRocket);
        existingRocket.setName(updatedRocketEntity.getName());
        existingRocket.setManufacturer(updatedRocketEntity.getManufacturer());
        existingRocket.setHeight(updatedRocketEntity.getHeight());
        existingRocket.setWeight(updatedRocketEntity.getWeight());
        existingRocket.setMissionType(updatedRocketEntity.getMissionType());
        existingRocket.setPartsCount(updatedRocketEntity.getPartsCount());

        rocketRepository.update(existingRocket, existingRocket.getId());

        return RocketMapper.mapToRocketDto(existingRocket);
    }

    @Override
    public void deleteRocket(Long rocketId) {
        Optional<Rocket> existingRocketOptional = rocketRepository.findById(rocketId);

        Rocket existingRocket = existingRocketOptional
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + rocketId));

        rocketRepository.delete(rocketId);
        log.info("Rocket with id " + rocketId + " has been deleted.");
    }

 //---------------------------------Image Upload---------------------------------


//    @Override
//    public RocketDto uploadRocketImage(Long rocketId, MultipartFile image) throws IOException {
//        // Rocket'ı ID'ye göre al
//        Rocket rocket = rocketRepository.findById(rocketId).orElseThrow(() -> new RuntimeException("Rocket not found"));
//
//        // Görsel dosyasını kaydet
//        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
//        Files.createDirectories(filePath.getParent()); // Dizin yoksa oluştur
//        Files.copy(image.getInputStream(), filePath);
//
//        // Görselin URL'sini oluştur
//        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/uploads/")
//                .path(fileName)
//                .toUriString();
//
//        // Rocket entity'sine resim URL'sini ekle
//        rocket.setImageUrl(imageUrl);
//        rocket = rocketRepository.save(rocket);
//
//        // Güncellenmiş RocketDto'yu döndür
//        return new RocketDto(rocket);
//    }
}
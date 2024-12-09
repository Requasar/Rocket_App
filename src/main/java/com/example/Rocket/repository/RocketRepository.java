package com.example.Rocket.repository;

import com.example.Rocket.entity.Rocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketRepository extends JpaRepository<Rocket, Long> {
}

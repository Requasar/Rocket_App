package com.example.Rocket.repository;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.entity.Rocket;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Repository
public class RocketRepository {

    private static final Logger log = LoggerFactory.getLogger(RocketRepository.class);
    private final JdbcClient jdbcClient;

    public RocketRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Rocket> findAll() {
        return jdbcClient.sql("select * from rocket1.rocket")
                .query(Rocket.class)
                .list();
    }

    public Optional<Rocket> findById(Long id) {
        return jdbcClient.sql("SELECT id,name,manufacturer,height,weight,missionType,partsCount FROM rocket1.rocket WHERE id = :id" )
                .param("id", id)
                .query(Rocket.class)
                .optional();
    }

    public void create(Rocket rocket) {
//        if (rocket.getVersion() == null) {
//            throw new IllegalArgumentException("Version cannot be null");
//        }
        // To add database
        var updated = jdbcClient.sql("INSERT INTO rocket1.rocket (name, manufacturer, height, weight, missionType, partsCount) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(
                        rocket.getName(),
                        rocket.getManufacturer(),
                        rocket.getHeight(),
                        rocket.getWeight(),
                        rocket.getMissionType(),
                        rocket.getPartsCount()
                        //rocket.getVersion()
                ))
                .update();
        Assert.state(updated == 1, "Failed to create Rocket " + rocket.getName());
    }


    public void update(Rocket rocket, Long id) {
        // Güncellenmiş SQL sorgusu
        var updated = jdbcClient.sql("UPDATE rocket1.rocket " +
                        "SET name = ?, manufacturer = ?, height = ?, weight = ?, missionType = ?, partsCount = ? " +
                        "WHERE id = ?")
                .params(List.of(
                        rocket.getName(),
                        rocket.getManufacturer(),
                        rocket.getHeight(),
                        rocket.getWeight(),
                        rocket.getMissionType(),
                        rocket.getPartsCount(),
                        id  // id ile güncelleme yapılacak
                ))
                .update();

        log.info("Update operation result: {}", updated);  // Güncelleme sonucu

        Assert.state(updated == 1, "Failed to update rocket " + rocket.getName() + " (id: " + id + ")");
    }


    public void delete(Long id) {
        var updated = jdbcClient.sql("DELETE FROM rocket1.rocket WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete rocket with id " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from rocket1.rocket").query().listOfRows().size();
    }

    public void saveAll(List<Rocket> runs) { //that can take a list of rockets and insert
        runs.stream().forEach(this::create);
    }

    /* for better databse performance batch insert can be used
    * public void saveAll(List<Rocket> rockets) {
    var sql = "INSERT INTO rocket.rocketv3 (name, manufacturer, height, weight, missionType, partsCount, version) VALUES (?, ?, ?, ?, ?, ?, ?)";
    var parameters = rockets.stream()
            .map(rocket -> List.of(
                    rocket.getName(),
                    rocket.getManufacturer(),
                    rocket.getHeight(),
                    rocket.getWeight(),
                    rocket.getMissionType(),
                    rocket.getPartsCount(),
                    rocket.getVersion()
            ))
            .collect(Collectors.toList());

    jdbcClient.batch(sql)
            .params(parameters)
            .update();
}
*/

}

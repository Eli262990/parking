package com.example.demo.api.base.repo;


import com.example.demo.api.base.model.ParkingSpot;
import com.example.demo.api.base.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findFirstByStatus(Status status);
    Integer countByStatus(Status status);

}

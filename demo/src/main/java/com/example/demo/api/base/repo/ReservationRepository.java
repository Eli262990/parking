package com.example.demo.api.base.repo;

import com.example.demo.api.base.model.Car;
import com.example.demo.api.base.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findByCarAndExitTimeIsNull(Car car);
    Reservation findByCarId(Long carId);
    Reservation findByLicensePlate(String licensePlate);
}

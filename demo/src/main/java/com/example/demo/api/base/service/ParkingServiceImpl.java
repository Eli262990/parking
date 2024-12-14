package com.example.demo.api.base.service;

import com.example.demo.api.base.dto.ParkingCapacityDTO;
import com.example.demo.api.base.dto.ReservationDTO;
import com.example.demo.api.base.model.*;
import com.example.demo.api.base.repo.CarRepository;
import com.example.demo.api.base.repo.CarTypeRepository;
import com.example.demo.api.base.repo.ParkingSpotRepository;
import com.example.demo.api.base.repo.ReservationRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.Validation;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CarTypeRepository carTypeRepository;

    public ParkingServiceImpl(CarRepository carRepository, ParkingSpotRepository parkingSpotRepository,
                              ReservationRepository reservationRepository,
                              CarTypeRepository carTypeRepository) {
        this.carRepository = carRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.reservationRepository = reservationRepository;
        this.carTypeRepository = carTypeRepository;
    }

    @Override
    public CarType addCarType(String carType){
        CarType carType1 = new CarType();
        carType1.setCarType(carType);
        carTypeRepository.save(carType1);
        return carType1;
    }

    @Override
    public void deleteByCarType(String carType) {
        CarType carType1 = carTypeRepository.findByCarType(carType);
        if (carType1 != null) {
            carTypeRepository.delete(carType1);
        } else {
            throw new CustomException(Validation.CAR_TYPE_NOT_FOUND);

        }
    }

    @Override
    public ReservationDTO saveReservation(Long carTypeId, String licensePlate) {
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new CustomException(Validation.LICENSE_PLATE_IS_NOT_EMPTY);
        }
        if (licensePlate.length() < 6) {
            throw new CustomException(Validation.LICENSE_PLATE_FORMAT);
        }
        Car existingCar = carRepository.findByLicensePlate(licensePlate);
        if (existingCar != null) {
            throw new CustomException(Validation.LICENSE_PLATE_IS_EXIST);
        }
        Optional<CarType> carTypeOptional = carTypeRepository.findById(carTypeId);

        if (carTypeOptional.isEmpty()) {
            throw new CustomException(Validation.CAR_TYPE_NOT_FOUND_WITH_ID);
        }
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setCarType(carTypeOptional.get());
        carRepository.save(car);
        LocalDateTime dateTime = LocalDateTime.now();
        ParkingSpot availableSpace = parkingSpotRepository.findFirstByStatus(Status.NULL)
                .orElseThrow(() -> new RuntimeException("No available parking space"));
        ReservationDTO reservationDTO = new ReservationDTO();
        ParkingSpot parkingSpot = availableSpace;
        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setId(carTypeId);
        reservation.setEntryTime(dateTime);
        reservation.setParkingSpot(parkingSpot);
        reservation.setLicensePlate(licensePlate);
        availableSpace.setStatus(Status.FILL);
        parkingSpot.setStatus(Status.FILL);
        parkingSpotRepository.save(parkingSpot);
        parkingSpotRepository.save(availableSpace);
        reservationRepository.save(reservation);
        reservationDTO.setCarTypeId(carTypeId);
        reservationDTO.setLicensePlate(car.getLicensePlate());
        reservationDTO.setEntryTime(reservation.getEntryTime());
        reservationDTO.setExitTime(reservation.getExitTime());
        reservationDTO.setSpotNumber(parkingSpot.getSpotNumber());
        reservationDTO.setFloorNumber(parkingSpot.getFloor().getFloorNumber());
        return reservationDTO;

    }

    @Override
    public String deleteCar(String licensePlate) {
        Car car = carRepository.findByLicensePlate(licensePlate);
        if (Objects.isNull(car)) {
            return "car not found";
        }
        Optional<Reservation> reservationF = reservationRepository.findByCarAndExitTimeIsNull(car);
        if (!reservationF.isPresent()) {
            return "reserve not found";
        }
        Reservation reservation = reservationF.get();
        reservation.setExitTime(LocalDateTime.now());
        ParkingSpot parkingSpot = reservation.getParkingSpot();
        parkingSpot.setStatus(Status.NULL);
        parkingSpotRepository.save(parkingSpot);
        reservationRepository.delete(reservation);
        carRepository.delete(car);
        return "car deleted successfully";

    }

    @Override
    public ParkingCapacityDTO getTotalCapacity() {

        Integer totalParkingSpots = (int) parkingSpotRepository.count();
        Integer occupiedSpots = parkingSpotRepository.countByStatus(Status.FILL);
        Integer availableSpots = totalParkingSpots - occupiedSpots;
        return ParkingCapacityDTO.builder()
                .availableSpot(availableSpots)
                .totalSpot(totalParkingSpots)
                .occupiedSpot(occupiedSpots)
                .build();
    }

    @Override
    public ReservationDTO calculateParkingCost(String licensePlate) {
        Integer hourlyFee = 10000;
        Integer entryFee = 15000;
        Integer dailyFee = 45000;

        Reservation reservation = reservationRepository.findByLicensePlate(licensePlate);
        LocalDateTime entryTime = reservation.getEntryTime();
        LocalDateTime exitTime = reservation.getExitTime();

        if (exitTime == null) {
            exitTime = LocalDateTime.now();
        }
        Duration duration = Duration.between(entryTime, exitTime);

        long hours = duration.toHours();
        if (hours > 24) {
            long totalFee = entryFee + dailyFee;
        }
        long totalFee = entryFee + (hours * hourlyFee);

        return ReservationDTO.builder()
                .entryTime(entryTime)
                .exitTime(exitTime)
                .licensePlate(licensePlate)
                .totalFee(totalFee)
                .build();
    }
}

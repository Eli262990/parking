package com.example.demo.api.base.Controller;

import com.example.demo.api.base.dto.ParkingCapacityDTO;
import com.example.demo.api.base.dto.ReservationDTO;
import com.example.demo.api.base.model.CarType;
import com.example.demo.api.base.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parking")

public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/add_CarType")
    public ResponseEntity<CarType> addCarType(@RequestBody CarType carType) {
        CarType savedCarType = parkingService.addCarType(carType.getCarType());
        return ResponseEntity.ok(savedCarType);
    }

    @DeleteMapping("/delete_car_type/{carType}")
    public ResponseEntity<String> deleteByCarType(@PathVariable String carType) {
        parkingService.deleteByCarType(carType);
        return ResponseEntity.ok("carType deleted successfully");
    }
//    @PostMapping("/select_car/{index}")
//    public String selectCar(@PathVariable int index) {
//        CarType[] carTypes = CarType.values();
//        if (index >= 0 && index < carTypes.length) {
//            return "your selected :" + carTypes[index];
//        } else
//            return "invalid index";
//    }


        @PostMapping("/add_car")
        public ReservationDTO saveReservation (@RequestBody @Validated ReservationDTO reservationDTO){
            return parkingService.saveReservation(reservationDTO.getCarTypeId(), reservationDTO.getLicensePlate());
        }


        @DeleteMapping("/delete_car/{licencePlate}")
        public ResponseEntity<String> deleteCar (@PathVariable String licencePlate){
            String result = parkingService.deleteCar(licencePlate);
            if (result.equals("car not found")) {
                return ResponseEntity.status(200).body(result);
            }
            return ResponseEntity.ok(result);
        }

        @GetMapping("/total_Capacity")
        public ParkingCapacityDTO getTotalCapacity () {
            return parkingService.getTotalCapacity();
        }


        @GetMapping("/Parking_Cost/{licencePlate}")
        public ReservationDTO calculateParkingCost (@PathVariable String licencePlate){
            return parkingService.calculateParkingCost(licencePlate);
        }

    }





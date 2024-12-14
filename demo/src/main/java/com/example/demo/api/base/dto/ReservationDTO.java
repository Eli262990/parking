package com.example.demo.api.base.dto;

import com.example.demo.api.base.model.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO implements Serializable {

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String licensePlate;
    private Long carTypeId;
    private int spotNumber;
    private int floorNumber;
    private long totalFee;

}

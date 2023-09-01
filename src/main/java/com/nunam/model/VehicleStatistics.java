package com.nunam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Long minutesInMovement;

    private Long minutesIdle;

    private Double kilometersTravelled;

    @ManyToOne
    @JoinColumn(name = "vehicleNumber")
    private Vehicle vehicle;
}


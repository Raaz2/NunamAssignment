package com.nunam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;

    //gps location
    private Double latitude;
    private Double longitude;

    private Double speed;

    @Enumerated(value = EnumType.STRING)
    private Status status; // RUNNING / IDLE

    @ManyToOne
    @JoinColumn(name = "vehicleNumber")
    @JsonIgnore
    private Vehicle vehicle;
}


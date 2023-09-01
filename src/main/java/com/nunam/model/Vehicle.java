package com.nunam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private Long vehicleNumber;

    @Column(name = "vehicle_load")
    private Double load;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<DataPoint> dataPoints = new ArrayList<>();
}

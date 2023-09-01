package com.nunam.service;

import com.nunam.model.DataPoint;
import com.nunam.model.VehicleStatistics;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleStatisticsService {
    void calculateAndStoreStatistics(LocalDate date, Optional<List<DataPoint>> dataPoints);

    VehicleStatistics calculateStatistics(List<DataPoint> dataPoints);
}

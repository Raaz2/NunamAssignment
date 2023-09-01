package com.nunam.service;

import com.nunam.model.DataPoint;
import com.nunam.model.Status;
import com.nunam.model.VehicleStatistics;
import com.nunam.repository.DataPointRepository;
import com.nunam.repository.VehicleStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatisticsServiceImpl implements VehicleStatisticsService {

    @Autowired
    private DataPointRepository dataPointRepository;

    @Autowired
    private VehicleStatisticsRepository vehicleStatisticsRepository;

    // Calculate and store statistics for a specific date
    public VehicleStatistics calculateAndStoreStatistics(LocalDate date) {
        List<DataPoint> dataPoints = dataPointRepository.findAll();
        long minutesInMovement = 0;
        long minutesIdle = 0;
        double totalKilometers = 0.0;

        // Calculate statistics based on dataPoints
        for (int i = 0; i < dataPoints.size() - 1; i++) {
            DataPoint current = dataPoints.get(i);
            DataPoint next = dataPoints.get(i + 1);

            if (Status.RUNNING.equals(current.getStatus())) {
                minutesInMovement += Duration.between(current.getTimestamp(), next.getTimestamp()).toMinutes();
                totalKilometers += calculateDistance(current.getLatitude(), current.getLongitude(), next.getLatitude(), next.getLongitude());
            } else {
                minutesIdle += Duration.between(current.getTimestamp(), next.getTimestamp()).toMinutes();
            }
        }

        // Create a new VehicleStatistics object and store it
        VehicleStatistics vehicleStatistics = new VehicleStatistics();
        vehicleStatistics.setDate(date);
        vehicleStatistics.setMinutesInMovement(minutesInMovement);
        vehicleStatistics.setMinutesIdle(minutesIdle);
        vehicleStatistics.setKilometersTravelled(totalKilometers);

        return vehicleStatisticsRepository.save(vehicleStatistics);
    }

    @Override
    public void calculateAndStoreStatistics(LocalDate date, Optional<List<DataPoint>> dataPoints) {

    }

    // Calculate statistics for a given list of DataPoints
    public VehicleStatistics calculateStatistics(List<DataPoint> dataPoints) {
        VehicleStatistics vehicleStatistics = new VehicleStatistics();

        if (dataPoints.isEmpty()) {
            return vehicleStatistics; // Return empty statistics if no data is available
        }

        // Initialize variables to accumulate statistics
        long minutesInMovement = 0;
        long minutesIdle = 0;
        double kilometersTravelled = 0.0;

        // Initialize timestamps for the first data point
        LocalDateTime previousTimestamp = dataPoints.get(0).getTimestamp();
        double previousLatitude = dataPoints.get(0).getLatitude();
        double previousLongitude = dataPoints.get(0).getLongitude();

        for (int i = 1; i < dataPoints.size(); i++) {
            DataPoint currentDataPoint = dataPoints.get(i);

            // Calculate time difference in minutes
            LocalDateTime currentTimestamp = currentDataPoint.getTimestamp();
            long timeDifferenceMinutes = Duration.between(previousTimestamp, currentTimestamp).toMinutes();

            if (Status.RUNNING.equals(currentDataPoint.getStatus())) {
                // If the vehicle is running, add time to minutes in movement
                minutesInMovement += timeDifferenceMinutes;

                // Calculate distance between two consecutive points and add it to kilometers travelled
                double currentLatitude = currentDataPoint.getLatitude();
                double currentLongitude = currentDataPoint.getLongitude();
                double distance = calculateDistance(previousLatitude, previousLongitude, currentLatitude, currentLongitude);
                kilometersTravelled += distance;
            } else {
                // If the vehicle is idle, add time to minutes idle
                minutesIdle += timeDifferenceMinutes;
            }

            // Update previous timestamp and location for the next iteration
            previousTimestamp = currentTimestamp;
            previousLatitude = currentDataPoint.getLatitude();
            previousLongitude = currentDataPoint.getLongitude();
        }

        // Set the calculated statistics in the VehicleStatistics object
        vehicleStatistics.setMinutesInMovement(minutesInMovement);
        vehicleStatistics.setMinutesIdle(minutesIdle);
        vehicleStatistics.setKilometersTravelled(kilometersTravelled);

        return vehicleStatisticsRepository.save(vehicleStatistics);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        final double R = 6371.0; // Earth's radius in km

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine formula
        double dlon = lon2Rad - lon1Rad;
        double dlat = lat2Rad - lat1Rad;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }
}


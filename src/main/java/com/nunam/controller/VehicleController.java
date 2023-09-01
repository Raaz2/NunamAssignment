package com.nunam.controller;

import com.nunam.exception.VehicleException;
import com.nunam.model.DataPoint;
import com.nunam.model.Vehicle;
import com.nunam.model.VehicleStatistics;
import com.nunam.repository.DataPointRepository;
import com.nunam.service.VehicleService;
import com.nunam.service.VehicleStatisticsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleStatisticsService vehicleStatisticsService;

    @Autowired
    private DataPointRepository dataPointRepository;

    @PostMapping("/addAll")
    public ResponseEntity<List<Vehicle>> createVehicleAll(@Valid @RequestBody List<Vehicle> vehicle) throws VehicleException {
        List<Vehicle> vehicle1 = vehicleService.addAllOnce(vehicle);
        return new ResponseEntity<>(vehicle1,HttpStatus.CREATED);
    }
    @GetMapping("/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicleByNumber(@PathVariable Long vehicleNumber) throws VehicleException {
        Vehicle vehicle = vehicleService.getVehicleByNumber(vehicleNumber);
        return new ResponseEntity<>(vehicle,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) throws VehicleException {
        Vehicle vehicle1 = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(vehicle1,HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleNumber}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String vehicleNumber, @Valid @RequestBody Vehicle vehicle) throws VehicleException {
        Vehicle newVehicle = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(newVehicle,HttpStatus.OK);
    }

    @DeleteMapping("/{vehicleNumber}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleNumber) throws VehicleException {
        vehicleService.deleteVehicleByNumber(vehicleNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/vehicles/statistics")
    public ResponseEntity<VehicleStatistics> getVehicleStatistics(
            @RequestParam String date) throws VehicleException {
        try {
            LocalDate localDate = LocalDate.parse(date);

            // Assuming you have a method to retrieve data points for a specific vehicle and date
            List<DataPoint> dataPoints = getDataPointsForVehicleAndDate(localDate);

            // Calculate statistics using the service
            VehicleStatistics statistics = vehicleStatisticsService.calculateStatistics(dataPoints);

            if (statistics != null) {
                return new ResponseEntity<>(statistics, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new VehicleException(e.getMessage());
        }
    }

    private List<DataPoint> getDataPointsForVehicleAndDate(LocalDate localDate) {
        return dataPointRepository.findByDate(localDate).get();
    }



}

/*
*
{
  "vehicleNumber": "VN001",
  "load": 20,
  "dataPoints": [
    {
      "time": "2023-09-01T12:46:03.253Z",
      "latitude": 35.8,
      "longitude": 300.7,
      "speed": 40,
      "status": "RUNNING"
    }
  ]
}
* */

package com.nunam.service;

import com.nunam.exception.VehicleException;
import com.nunam.model.Vehicle;
import com.nunam.repository.DataPointRepository;
import com.nunam.repository.VehicleRepository;
import com.nunam.repository.VehicleStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    DataPointRepository dataPointRepository;
    @Autowired
    VehicleStatisticsRepository vehicleStatisticsRepository;
    @Override
    public List<Vehicle> addAllOnce(List<Vehicle> vehicleList) {
        vehicleList.forEach(i -> i.getDataPoints().get(0).setVehicle(i));
        return vehicleRepository.saveAll(vehicleList);
    }
    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws VehicleException {
        vehicle.getDataPoints().get(0).setVehicle(vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleByNumber(Long vehicleNumber) throws VehicleException {
        return vehicleRepository.findById(vehicleNumber).orElseThrow(() -> new VehicleException("No vehicle present with the vehicle number: " + vehicleNumber));
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) throws VehicleException {
        // Check if the vehicle exists
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(vehicle.getVehicleNumber());

        if (existingVehicle.isPresent()) {
            // Vehicle exists, update it
            Vehicle existingVehicle1 = existingVehicle.get();

            // Perform updates on the existing vehicle
            existingVehicle1.setLoad(vehicle.getLoad());

            return vehicleRepository.save(existingVehicle1);
        } else {
            throw new VehicleException("No vehicle present with the vehicle number: " + vehicle.getVehicleNumber());
        }
    }

    @Override
    public void deleteVehicleByNumber(Long vehicleNumber) throws VehicleException {
         Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleNumber);
        if (vehicle.isPresent()) {
            vehicleRepository.delete(vehicle.get());
        } else throw new VehicleException("No vehicle present with the vehicle number: " + vehicleNumber);
    }

}

package com.nunam.service;

import com.nunam.exception.VehicleException;
import com.nunam.model.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    /**
     * Create a new vehicle
     * @param vehicle the vehicle to create
     * @return the created vehicle
     */
    Vehicle addVehicle(Vehicle vehicle) throws VehicleException;

    /**
     * find  a new vehicle
     * @param vehicleNumber the vehicle to find
     * @return the vehicle found
     */
    Vehicle getVehicleByNumber(Long vehicleNumber) throws VehicleException;


    Vehicle updateVehicle(Vehicle vehicle) throws VehicleException;

    void deleteVehicleByNumber(Long vehicleNumber) throws VehicleException;


    public List<Vehicle> addAllOnce(List<Vehicle> vehicleList);
}

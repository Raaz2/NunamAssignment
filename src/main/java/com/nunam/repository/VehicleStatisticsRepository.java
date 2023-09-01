package com.nunam.repository;

import com.nunam.model.VehicleStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VehicleStatisticsRepository extends JpaRepository<VehicleStatistics, Long> {

}

package com.amadeuscasestudy.AmadeusCaseStudy.repository;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<List<Airport>> getAirPortByCity(String city);
    Airport getAirportById(Long id);

    Optional<Airport> getAirportByAirportName(String airportName);
}

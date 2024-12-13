package com.amadeuscasestudy.AmadeusCaseStudy.repository;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    Flight getFlightById(Long id);

//    List<Flight> findFlightByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate
//            (Airport departureAirport, Airport arrivalAirport,
//             LocalDateTime departureDate, LocalDateTime returnDate);

    @Query(nativeQuery = true,
            value = "SELECT * FROM FLIGHT WHERE DEPARTURE_AIRPORT_ID = :departureAirportId AND ARRIVAL_AIRPORT_ID = :arrivalAirportId AND DEPARTURE_DATE = :departureDate")
    List<Flight> findFlightByDepartureAirportAndArrivalAirportAndDepartureDate
            (@Param("departureAirportId") Long departureAirportId, @Param("arrivalAirportId") Long arrivalAirportId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate);

    @Query(nativeQuery = true,
            value = "SELECT * FROM FLIGHT WHERE DEPARTURE_AIRPORT_ID = :departureAirportId AND ARRIVAL_AIRPORT_ID = :arrivalAirportId AND DEPARTURE_DATE = :departureDate AND RETURN_DATE = :returnDate")
    List<Flight> findFlightByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate
            (@Param("departureAirportId") Long departureAirportId, @Param("arrivalAirportId") Long arrivalAirportId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate);



}

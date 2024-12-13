package com.amadeuscasestudy.AmadeusCaseStudy.service;

import com.amadeuscasestudy.AmadeusCaseStudy.dto.request.FlightRequest;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.FlightRepository;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchService {
    private final FlightRepository flightRepository;

    public FlightSearchService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchOnewayFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDate){
            return flightRepository.findFlightByDepartureAirportAndArrivalAirportAndDepartureDate(departureAirportId,arrivalAirportId,departureDate);
    }

    public List<Flight> twowayFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate){
        List<Flight> twowayFlights = new ArrayList<>();
        List<Flight> oneWayFlights = flightRepository.findFlightByDepartureAirportAndArrivalAirportAndDepartureDate(
                departureAirportId,arrivalAirportId,departureDate);
        List<Flight> returnFlights = flightRepository.findFlightByDepartureAirportAndArrivalAirportAndDepartureDate(arrivalAirportId,departureAirportId,returnDate);//replace arrival-departure

        twowayFlights.addAll(oneWayFlights);
        twowayFlights.addAll(returnFlights);
        return twowayFlights;
    }



}

package com.amadeuscasestudy.AmadeusCaseStudy.controller;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import com.amadeuscasestudy.AmadeusCaseStudy.service.FlightSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService){
        this.flightSearchService = flightSearchService;
    }
    @GetMapping("searchFlights/OnewayFlights")
    public List<Flight> searchOnewayFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate){
        List<Flight> flightList = flightSearchService.searchOnewayFlights(departureAirportId,arrivalAirportId,departureDate);
        return flightList;
    }

    @GetMapping("searchFlights/twowayFlights")
    public List<Flight> twowayFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate){
        return flightSearchService.twowayFlights(departureAirportId,arrivalAirportId,departureDate,returnDate);
    }

}

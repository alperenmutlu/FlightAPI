package com.amadeuscasestudy.AmadeusCaseStudy.controller;

import com.amadeuscasestudy.AmadeusCaseStudy.dto.request.FlightRequest;
import com.amadeuscasestudy.AmadeusCaseStudy.dto.response.FlightResponse;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import com.amadeuscasestudy.AmadeusCaseStudy.service.FlightSearchService;
import com.amadeuscasestudy.AmadeusCaseStudy.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {

    private final FlightService flightService;
    private final FlightSearchService flightSearchService;

    public FlightController(FlightService flightService, FlightSearchService flightSearchService){
        this.flightService = flightService;
        this.flightSearchService = flightSearchService;
    }

    @PostMapping("flights")
    public void saveFlight(@RequestBody FlightRequest flightRequest){
        flightService.saveFlight(flightRequest);
    }

    @DeleteMapping("/flights/{id}")
    public String deleteFlight(@RequestParam Long id){
            return flightService.deleteFlight(id);
    }

    @GetMapping("flights/{id}")
    public FlightResponse getFlightById(Long id){
        FlightResponse flightResponse = new FlightResponse();
        Optional<Flight> flight = flightService.getFlightById(id);
        if(flight.isPresent()){
            flightResponse.setDepartureAirport(flight.get().getDepartureAirport());
            flightResponse.setArrivalAirport(flight.get().getArrivalAirport());
            flightResponse.setDepartureDate(flight.get().getDepartureDate());
            flightResponse.setReturnDate(flight.get().getReturnDate());
            flightResponse.setPrice(flight.get().getPrice());
            return flightResponse;

        }
        return null; //Should be replaced with a special type
    }

    @GetMapping("flights")
    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    @PutMapping("/flights/update/{id}")
    public String updateFlightById(@RequestBody FlightRequest flightRequest, Long id){
        return flightService.updateFlightById(flightRequest,id);
    }

}

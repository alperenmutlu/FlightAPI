package com.amadeuscasestudy.AmadeusCaseStudy.controller;

import com.amadeuscasestudy.AmadeusCaseStudy.dto.request.AirportRequest;
import com.amadeuscasestudy.AmadeusCaseStudy.dto.response.AirportResponse;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @PostMapping("/airports")
    public String saveAirPort(@RequestBody AirportRequest airportRequest){
      return airportService.saveAirPort(airportRequest);
    }

    @DeleteMapping("/airports")
    public String deleteAirport(@RequestBody AirportRequest airportRequest){
       return airportService.deleteAirport(airportRequest);
    }

    @PutMapping("/airports/update/{id}")
    public String updateAirport(@RequestBody AirportRequest airportRequest, @PathVariable Long id){
       return airportService.updateAirPort(airportRequest,id);
    }

    @GetMapping("/airports/city/{city}")
    public Optional<List<Airport>> getAirportsByCity(@PathVariable String city) throws Exception {
         return airportService.getAirportsByCity(city);
    }

    @GetMapping("/airports/id")
    public Optional<AirportResponse> getAirPortById(@PathVariable Long id){
        Optional<Airport> airport = airportService.getAirportById(id);
        Optional<AirportResponse> airportResponse;
        if(airport.isPresent()){
            airportResponse = Optional.of(new AirportResponse());
            airportResponse.get().setCity(airport.get().getCity());
            airportResponse.get().setAirportName(airport.get().getAirportName());
            return airportResponse;
        }

        return null; //Should be replaced with special type
    }

    @GetMapping("/airports")
    public List<Airport> getAllAirPorts(){
        return airportService.getAllAirports();
    }

}

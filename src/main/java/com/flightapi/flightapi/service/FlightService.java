package com.amadeuscasestudy.AmadeusCaseStudy.service;

import com.amadeuscasestudy.AmadeusCaseStudy.dto.request.FlightRequest;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.AirportRepository;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository){
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public void saveFlight(FlightRequest flightRequest){
        Airport arrivalAirPort = new Airport();
        Airport departureAirtport = new Airport();
        departureAirtport.setId(flightRequest.getDepartureAirport().getId());
        arrivalAirPort.setId(flightRequest.getArrivalAirport().getId());

        Flight flight = new Flight();
        flight.setDepartureAirport(departureAirtport);
        flight.setArrivalAirport(arrivalAirPort);
        flight.setDepartureDate(flightRequest.getDepartureDate());
        flight.setReturnDate(flightRequest.getReturnDate());
        flight.setPrice(flightRequest.getPrice());

        flightRepository.save(flight);
    }

    public String deleteFlight(Long id){
        Optional<Flight> flightToDelete = flightRepository.findById(id);
        Flight flight = new Flight();
        if(flightToDelete.isPresent()){
        flight.setId(flightToDelete.get().getId());
            flightRepository.delete(flight);
            return "Flight is deleted successfully with the id: " + flightToDelete.get().getId();
        }
        return "Flight is not found with the id: " + id;
    }

    public Optional<Flight> getFlightById(Long id){
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight != null){
            return flight;
        }else {
            return Optional.empty();
        }
    }

    public List<Flight> getAllFlights(){
        List<Flight> flights = new ArrayList<>();
        flights.addAll(flightRepository.findAll());
        return flights;
    }

    public String updateFlightById(FlightRequest flightRequest,Long id){
        Flight flight = flightRepository.getFlightById(id);
        if(flight != null){
            flight.setDepartureAirport(flightRequest.getDepartureAirport());
            flight.setArrivalAirport(flightRequest.getArrivalAirport());
            flight.setDepartureDate(flightRequest.getDepartureDate());
            flight.setReturnDate(flightRequest.getReturnDate());
            flight.setPrice(flightRequest.getPrice());
            flightRepository.save(flight);
            return "Flight is updated with the id: " + id;
        }else{
            return "Flight is not found with the id: " + id;
        }

    }



}

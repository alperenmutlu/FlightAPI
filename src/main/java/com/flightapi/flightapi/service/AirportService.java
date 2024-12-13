package com.amadeuscasestudy.AmadeusCaseStudy.service;

import com.amadeuscasestudy.AmadeusCaseStudy.dto.request.AirportRequest;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public String saveAirPort(AirportRequest airportRequest){
        Airport airport = new Airport();

        if(!getAirportByName(airportRequest.getAirportName())){
            airport.setCity(airportRequest.getCity());
            airport.setAirportName(airportRequest.getAirportName());
            airportRepository.save(airport);
            return "Airport added successfully";
        }
        return "The airport is already exist with the given name: " + airportRequest.getAirportName();
    }

    public String updateAirPort(AirportRequest airportRequest,Long id){
        Airport airportToUpdate = airportRepository.getAirportById(id);

        if(airportToUpdate != null){
            airportToUpdate.setCity(airportRequest.getCity());
            airportToUpdate.setAirportName(airportRequest.getAirportName());
            airportRepository.save(airportToUpdate);
            return "Airport is updated with the id: " + id;
        }

        return "Airport is not found with the id: " + id;
    }

    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    public Optional<List<Airport>> getAirportsByCity(String city) throws Exception {
        //List<AirportResponse> airportResponseList = new ArrayList<>();
        Optional<List<Airport>> airportList = airportRepository.getAirPortByCity(city);

        return airportList;

    }

   public Optional<Airport> getAirportById(Long id){
       Optional<Airport> airport = airportRepository.findById(id);
       if(airport.isPresent()) {
           return airport;
       }
       return null; //Should be replaced with a response type
   }

   public String deleteAirport(AirportRequest airportRequest){
        Optional<Airport> airport = airportRepository.getAirportByAirportName(airportRequest.getAirportName());
        if(airport.isPresent()){
            Airport airportToDelete = airportRepository.getAirportById(airport.get().getId());
            airportRepository.delete(airportToDelete);
            return "Airport deleted successfully";
        }
        return "Failed to delete"; //Should be replaced with a  speacial response type
   }

    private Boolean getAirportByName(String name){
       Optional<Airport> airport = airportRepository.getAirportByAirportName(name);
       if(airport.isPresent()){
          if(airport.get().getAirportName().toLowerCase().equals(name.toLowerCase())){
              return true;
           }
       }
       return false;
    }



}

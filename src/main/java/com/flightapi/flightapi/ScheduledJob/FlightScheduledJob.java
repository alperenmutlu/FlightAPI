package com.amadeuscasestudy.AmadeusCaseStudy.ScheduledJob;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.AirportRepository;
import com.amadeuscasestudy.AmadeusCaseStudy.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@Slf4j
public class FlightScheduledJob {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final RestTemplate restTemplate;

    public FlightScheduledJob(FlightRepository flightRepository, AirportRepository airportRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void fetchAndSaveFlightData() {
        String apiUrl = "http://localhost:8081/mockapi/flights";

        Flight[] flights = restTemplate.getForObject(apiUrl, Flight[].class);
        if (flights != null) {
            for (Flight flight : flights) {
                Airport departureAirport = flight.getDepartureAirport();
                Airport arrivalAirport = flight.getArrivalAirport();

                airportRepository.save(departureAirport);
                airportRepository.save(arrivalAirport);

                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);
            }

            flightRepository.saveAll(Arrays.asList(flights));
            log.info("Flight data fetched from mock-api and saved successfully.");
        }
        else {
            log.info("Failed to fetch flight data.");
        }
    }

}

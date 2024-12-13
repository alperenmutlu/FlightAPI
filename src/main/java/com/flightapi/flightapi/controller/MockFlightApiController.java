package com.amadeuscasestudy.AmadeusCaseStudy.controller;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import com.amadeuscasestudy.AmadeusCaseStudy.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/mockapi")
public class MockFlightApiController {
    @GetMapping("/flights")
    public List<Flight> getMockFlights() {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            Flight flight = new Flight();
            flight.setId((long) i);

            Long testId = 20L;
            Long testId2 = 30L;
            Airport departureAirport =new Airport();
            departureAirport.setId(testId+i);
            departureAirport.setAirportName("test" + testId+i);
            departureAirport.setCity("testCity + " +"test" + testId+i);

            flight.setDepartureAirport(departureAirport);

            Airport arrivalAirport = new Airport();
            arrivalAirport.setId(testId2+i);
            arrivalAirport.setAirportName("test" + testId2+i);
            arrivalAirport.setCity("testCity" + "test2" + testId2+i);
            flight.setArrivalAirport(arrivalAirport);

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime departureDateTime = now.plusDays(i).plusHours(random.nextInt(24)).plusMinutes(random.nextInt(60));
            LocalDateTime returnDateTime = departureDateTime.plusDays(random.nextInt(2)).plusHours(random.nextInt(24)).plusMinutes(random.nextInt(60));

            flight.setDepartureDate(departureDateTime);
            flight.setReturnDate(returnDateTime);

            BigDecimal price = BigDecimal.valueOf(500 + random.nextInt(1000));
            flight.setPrice(price);

            flights.add(flight);
        }

        return flights;
    }
}

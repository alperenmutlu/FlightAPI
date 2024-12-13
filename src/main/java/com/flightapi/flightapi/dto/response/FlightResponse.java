package com.amadeuscasestudy.AmadeusCaseStudy.dto.response;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightResponse {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private BigDecimal price;
}

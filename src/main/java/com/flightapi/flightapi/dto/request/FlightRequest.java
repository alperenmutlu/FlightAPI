package com.amadeuscasestudy.AmadeusCaseStudy.dto.request;

import com.amadeuscasestudy.AmadeusCaseStudy.model.Airport;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class FlightRequest {
   private Airport departureAirport;
   private Airport arrivalAirport;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private LocalDateTime departureDate;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private LocalDateTime returnDate;
   private BigDecimal price;
}

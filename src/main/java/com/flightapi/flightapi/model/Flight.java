package com.amadeuscasestudy.AmadeusCaseStudy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_AIRPORT_ID",nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_AIRPORT_ID",nullable = false)
    private Airport arrivalAirport;

    @Column(name="DEPARTURE_DATE",nullable = false)
    private LocalDateTime departureDate;

    @Column(name="RETURN_DATE")
    private LocalDateTime returnDate;

    @Column(name="PRICE",nullable = false)
    private BigDecimal price;


}

package com.amadeuscasestudy.AmadeusCaseStudy.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CITY",nullable = false)
    private String city;

    @Column(name ="AIRPORT_NAME",nullable = false)
    private String airportName;


}

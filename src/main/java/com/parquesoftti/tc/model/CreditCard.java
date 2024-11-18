package com.parquesoftti.tc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creditcard" ,schema = "public")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private  String cardNumber;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false, length = 4)
    private String cvv;


//    CREATE TABLE CreditCard (
//            id serial PRIMARY KEY,
//            card_number VARCHAR(16) NOT NULL,
//    expiration_date DATE NOT NULL,
//    cvv VARCHAR(3) NOT NULL
//);


}

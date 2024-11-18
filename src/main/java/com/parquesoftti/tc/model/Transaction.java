package com.parquesoftti.tc.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
/*@Builder*/
@Table(name = "transaction" ,schema = "public")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

    public Long getCreditCardId() {
        return (creditCard != null) ? creditCard.getId() : null;
    }
}

//    CREATE TABLE Transaction (
//            id serial  PRIMARY KEY,
//            amount DECIMAL(10,2) NOT NULL,
//    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    status VARCHAR(20) NOT NULL,
//    credit_card_id BIGINT,
//    FOREIGN KEY (credit_card_id) REFERENCES CreditCard(id)
//            );


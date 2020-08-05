package com.demo.microservices.beans;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "currency_from")
    private String from;

    @NonNull
    @Column(name = "currency_to")
    private String to;

    @NonNull
    private BigDecimal conversionMultiple;

    private int port;
}

package com.demo.microservices.repository;

import com.demo.microservices.beans.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromAndToIgnoreCase(String from,String to);
}

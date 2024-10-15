package com.adr.comercio.infrastructure.persistence.repository;

import com.adr.comercio.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
   List<Price> findByBrandIdAndProductIdAndApplicationDate(int brandId, int productId, LocalDateTime applicationDate);
}


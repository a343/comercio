package com.adr.comercio.domain.repository;

import com.adr.comercio.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String brandId,
            String productId,
            OffsetDateTime fechaInicio,
            OffsetDateTime fechaFin);

}

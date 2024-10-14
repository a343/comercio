package com.adr.comercio.domain.service.port.out;

import com.adr.comercio.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int brandId,
            int productId,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

}

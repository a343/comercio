package com.adr.comercio.infrastructure.persistence.repository.jpa;

import com.adr.comercio.infrastructure.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int brandId,
            int productId,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

}

package com.adr.comercio.infrastructure.persistence.repository.jpa;

import com.adr.comercio.domain.model.PriceVO;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
   List<PriceVO> findByBrandIdAndProductIdAndApplicationDate(int brandId, int productId, LocalDateTime applicationDate);
}


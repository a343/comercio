package com.adr.comercio.domain.service.port;

import com.comercio.aplicacion.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDTO getPriceInfoByProduct(final String brandId, final String productId, final LocalDateTime applicationDate);
}

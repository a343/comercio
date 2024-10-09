package com.adr.comercio.domain.service.port;

import com.comercio.aplicacion.dto.PriceDTO;

import java.time.OffsetDateTime;

public interface PriceService {

    PriceDTO getPriceInfoByProduct(final String brandId, final String productId, final OffsetDateTime applicationDate);
}

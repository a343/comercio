package com.adr.comercio.domain.service.port.in;

import com.comercio.aplicacion.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDTO getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate);
}

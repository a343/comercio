package com.adr.comercio.domain.port;


import com.comercio.application.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PricePort {

    PriceDTO getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate);
}

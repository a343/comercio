package com.adr.comercio.domain.port;

import com.adr.comercio.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {

    Price getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate);
}

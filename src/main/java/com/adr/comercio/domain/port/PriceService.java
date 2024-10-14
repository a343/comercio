package com.adr.comercio.domain.port;

import com.adr.comercio.domain.model.PriceVO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceVO getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate);
}

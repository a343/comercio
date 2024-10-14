package com.adr.comercio.application.adapater;

import com.adr.comercio.application.converter.PriceVOConverter;
import com.adr.comercio.domain.port.PricePort;
import com.adr.comercio.domain.port.PriceService;
import com.comercio.application.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PriceRestAdapter implements PricePort {

    private final PriceVOConverter priceConverter;
    private final PriceService priceServiceç;

    @Override
    public PriceDTO getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate) {
        return priceConverter.convert(priceServiceç.getPriceInfoByProduct(brandId, productId, applicationDate));
    }
}

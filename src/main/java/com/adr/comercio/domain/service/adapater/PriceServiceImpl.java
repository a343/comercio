package com.adr.comercio.domain.service.adapater;

import com.adr.comercio.application.converters.PriceConverter;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.repository.PriceRepository;
import com.adr.comercio.domain.service.port.PriceService;
import com.adr.comercio.infrastructure.controller.PriceController;
import com.comercio.aplicacion.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceConverter priceConverter;

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);


    @Override
    public PriceDTO getPriceInfoByProduct(String brandId, String productId, OffsetDateTime applicationDate) {

        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate,applicationDate);
        Price price = prices.stream().max((p1, p2) -> p1.getPriority().compareTo(p2.getPriority())).get();

        logger.debug("Got price from database ", price);

        return priceConverter.convert(price);
    }
}

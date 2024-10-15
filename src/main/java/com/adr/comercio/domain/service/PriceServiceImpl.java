package com.adr.comercio.domain.service;

import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.port.PriceService;
import com.adr.comercio.infrastructure.persistence.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);


    @Override
    public Price getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate) {

        final List<Price> prices = priceRepository
                .findByBrandIdAndProductIdAndApplicationDate( brandId,  productId,  applicationDate);

        final Price price = prices.stream()
                .max(Comparator.comparing(Price::getPriority)).get();

        logger.debug("Got price from database: {}", price);

        return price;
    }

}

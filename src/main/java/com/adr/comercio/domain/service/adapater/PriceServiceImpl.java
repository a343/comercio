package com.adr.comercio.domain.service.adapater;

import com.adr.comercio.application.converters.PriceConverter;
import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.service.port.out.PriceRepository;
import com.adr.comercio.domain.service.port.in.PriceService;
import com.comercio.aplicacion.dto.ErrorDTO;
import com.comercio.aplicacion.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceConverter priceConverter;

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);


    @Override
    public PriceDTO getPriceInfoByProduct(final int brandId, final int productId, final LocalDateTime applicationDate) {
        final List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate);

        if (prices.isEmpty()) {

            throw new PriceException(HttpStatus.NOT_FOUND, ErrorDTO.builder().message("There is no product with these characteristics").build());
        }

        final Price price = prices.stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElse(null);

        logger.debug("Got price from database: {}", price);

        return priceConverter.convert(price);
    }

}

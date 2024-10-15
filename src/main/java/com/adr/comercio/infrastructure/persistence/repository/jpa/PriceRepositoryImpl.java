package com.adr.comercio.infrastructure.persistence.repository.jpa;

import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.infrastructure.persistence.converter.PriceConverter;
import com.adr.comercio.infrastructure.persistence.model.PriceEntity;
import com.adr.comercio.infrastructure.persistence.repository.PriceRepository;
import com.comercio.application.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;
    private final PriceConverter priceConverter;

    public PriceRepositoryImpl(final JpaPriceRepository jpaPriceRepository, final PriceConverter priceConverter) {
        this.jpaPriceRepository = jpaPriceRepository;
        this.priceConverter = priceConverter;
    }

    @Override
    public List<Price> findByBrandIdAndProductIdAndApplicationDate(final int brandId, final int productId, final LocalDateTime applicationDate) {
        final List<PriceEntity> prices = jpaPriceRepository
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate);

        if (prices.isEmpty()) {
            throw new PriceException(HttpStatus.NOT_FOUND,
                    ErrorDTO.builder().message("There is no product with these characteristics")
                            .build());
        }

        return priceConverter.convert(prices);
    }

}


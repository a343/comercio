package com.adr.comercio.infrastructure.persistence.repository.jpa;

import com.adr.comercio.application.dto.Errors;
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
import java.util.Optional;

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
        List<PriceEntity> prices = jpaPriceRepository
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate);

        return Optional.of(prices)
                .filter(priceList -> !priceList.isEmpty())
                .map(priceConverter::convert)
                .orElseThrow(() -> createPriceNotFoundException());
    }

    private PriceException createPriceNotFoundException() {
        return new PriceException(HttpStatus.NOT_FOUND,
                ErrorDTO.builder().message(Errors.ERROR_NOT_FOUND)
                        .build());
    }


}


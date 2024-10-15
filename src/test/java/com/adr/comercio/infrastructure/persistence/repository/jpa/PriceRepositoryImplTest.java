package com.adr.comercio.infrastructure.persistence.repository.jpa;

import com.adr.comercio.application.dto.Errors;
import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.infrastructure.persistence.converter.PriceConverter;
import com.adr.comercio.infrastructure.persistence.model.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @Mock
    private PriceConverter priceConverter;

    @InjectMocks
    private PriceRepositoryImpl priceRepositoryImpl;

    @Test
    void findByBrandIdAndProductIdAndApplicationDate_ReturnsPrices() {
        final int brandId = 1;
        final int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        final PriceEntity priceEntity1 = new PriceEntity();
        final PriceEntity priceEntity2 = new PriceEntity();
        final List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2);

        final Price price1 = new Price();
        final Price price2 = new Price();
        final List<Price> expectedPrices = List.of(price1, price2);

        when(jpaPriceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate)).thenReturn(priceEntities);
        when(priceConverter.convert(priceEntities)).thenReturn(expectedPrices);

        final List<Price> result = priceRepositoryImpl.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(jpaPriceRepository, times(1)).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate);
        verify(priceConverter, times(1)).convert(priceEntities);
    }

    @Test
    void findByBrandIdAndProductIdAndApplicationDate_ThrowsPriceException_WhenNoPricesFound() {
        final int brandId = 1;
        final int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        when(jpaPriceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate)).thenReturn(Collections.emptyList());

        final PriceException exception = assertThrows(PriceException.class, () -> priceRepositoryImpl.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate));

        assertEquals(HttpStatus.NOT_FOUND, exception.getErroCode());
        assertEquals(Errors.ERROR_NOT_FOUND, exception.getError().getMessage());

        verify(jpaPriceRepository, times(1)).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, applicationDate, applicationDate);
        verify(priceConverter, never()).convert(anyList());
    }
}

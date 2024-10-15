package com.adr.comercio.domain.service;

import com.adr.comercio.domain.model.Price;
import com.adr.comercio.infrastructure.persistence.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void getPriceInfoByProduct_ReturnsCorrectPrice() {
        int brandId = 1;
        int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        final Price price1 = new Price();
        price1.setPriority(1L);

        final Price price2 = new Price();
        price2.setPriority(2L);

        when(priceRepository.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate))
                .thenReturn(List.of(price1, price2));

        Price result = priceService.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(2, result.getPriority());
        verify(priceRepository, times(1)).findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate);
    }

    @Test
    void getPriceInfoByProduct_ThrowsNoSuchElementException_WhenNoPrices() {
        int brandId = 1;
        int productId = 100;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepository.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate))
                .thenReturn(List.of());

        assertThrows(NoSuchElementException.class, () ->
                priceService.getPriceInfoByProduct(brandId, productId, applicationDate)
        );

        verify(priceRepository, times(1)).findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate);
    }


}



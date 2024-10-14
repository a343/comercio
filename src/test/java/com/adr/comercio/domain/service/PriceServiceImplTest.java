package com.adr.comercio.domain.service;

import com.adr.comercio.application.converters.PriceConverter;
import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.service.port.out.PriceRepository;
import com.adr.comercio.domain.service.adapater.PriceServiceImpl;
import com.comercio.aplicacion.dto.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceConverter priceConverter;

    @InjectMocks
    private PriceServiceImpl priceService;


    @Test
    void testGetPriceInfoByProduct_NoPricesFound() {
        // Arrange
        int brandId = 1;
        int productId = 1;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, applicationDate, applicationDate)).thenReturn(Collections.emptyList());

        PriceException exception = assertThrows(PriceException.class, () -> {
            priceService.getPriceInfoByProduct(brandId, productId, applicationDate);
        });

        // Verificar el mensaje de la excepción
        assertEquals("There is no product with these characteristics", exception.getError().getMessage());
    }


    @Test
    void getPriceInfoByProduct_PriceFound_ReturnsHighestPriorityPrice() {
        Price price1 = Price.builder()
                .priority(1L)
                .build();

        Price price2 = Price.builder()
                .priority(2L)
                .build();

        List<Price> prices = Arrays.asList(price1, price2);

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any()))
                .thenReturn(prices);

        PriceDTO priceDTO = PriceDTO.builder().build();
        when(priceConverter.convert(price2)).thenReturn(priceDTO);

        PriceDTO result = priceService.getPriceInfoByProduct(1, 1234, LocalDateTime.now());

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(priceRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any());
        verify(priceConverter).convert(price2);
    }

    @Test
    void getPriceInfoByProduct_PriceFoundWithSamePriority_ReturnsFirst() {
        Price price1 = Price.builder()
                .priority(1L)
                .build();

        Price price2 = Price.builder()
                .priority(1L)
                .build();

        List<Price> prices = Arrays.asList(price1, price2);

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any()))
                .thenReturn(prices);

        PriceDTO priceDTO = PriceDTO.builder().build();
        when(priceConverter.convert(price1)).thenReturn(priceDTO);

        PriceDTO result = priceService.getPriceInfoByProduct(1, 1234, LocalDateTime.now());

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(priceRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any());
        verify(priceConverter).convert(price1);
    }
}


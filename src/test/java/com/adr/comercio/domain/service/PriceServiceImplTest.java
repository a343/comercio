package com.adr.comercio.domain.service;

import com.adr.comercio.application.converters.PriceConverter;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.repository.PriceRepository;
import com.adr.comercio.domain.service.adapater.PriceServiceImpl;
import com.comercio.aplicacion.dto.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceConverter priceConverter;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPriceInfoByProduct_NoPrices_ReturnsNull() {
        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any()))
                .thenReturn(Collections.emptyList());

        PriceDTO result = priceService.getPriceInfoByProduct("brand1", "product1", LocalDateTime.now());

        assertNull(result);
        verify(priceRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any());
        verifyNoInteractions(priceConverter);
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

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any()))
                .thenReturn(prices);

        PriceDTO priceDTO = PriceDTO.builder().build();
        when(priceConverter.convert(price2)).thenReturn(priceDTO);

        PriceDTO result = priceService.getPriceInfoByProduct("brand1", "product1", LocalDateTime.now());

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(priceRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any());
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

        when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any()))
                .thenReturn(prices);

        PriceDTO priceDTO = PriceDTO.builder().build();
        when(priceConverter.convert(price1)).thenReturn(priceDTO);

        PriceDTO result = priceService.getPriceInfoByProduct("brand1", "product1", LocalDateTime.now());

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(priceRepository).findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(), anyString(), any(), any());
        verify(priceConverter).convert(price1);
    }
}


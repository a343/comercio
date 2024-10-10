package com.adr.comercio.infrastructure.controller;

import com.adr.comercio.domain.service.port.PriceService;
import com.comercio.aplicacion.dto.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceInfoByProduct_Success() {
        final String brandId = "1";
        final String productId = "35455";
        final String currency = "EUR";

        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);


        PriceDTO priceDTO = PriceDTO.builder().price(BigDecimal.TEN).brandId(brandId).productId(productId).currency(currency).startDate(LocalDateTime.of(2020, 6, 15, 0, 0)).endDate(LocalDateTime.of(2020, 6, 15, 11, 0)).build();

        when(priceService.getPriceInfoByProduct(brandId, productId, applicationDate)).thenReturn(priceDTO);

        ResponseEntity<PriceDTO> response = priceController.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(priceDTO, response.getBody());
        verify(priceService).getPriceInfoByProduct(brandId, productId, applicationDate);
    }

    @Test
    void testGetPriceInfoByProduct_NoContent() {
        final String brandId = "1";
        final String productId = "35455";
        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);

        when(priceService.getPriceInfoByProduct(brandId, productId, applicationDate)).thenReturn(null);

        ResponseEntity<PriceDTO> response = priceController.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(priceService).getPriceInfoByProduct(brandId, productId, applicationDate);
    }
}

package com.adr.comercio.application.controller;

import com.adr.comercio.application.controller.PriceController;
import com.adr.comercio.domain.service.port.in.PriceService;
import com.comercio.aplicacion.dto.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;


    @Test
    void testGetPriceInfoByProduct_Success() {
        final int brandId = 1;
        final int productId = 35455;
        final String currency = "EUR";

        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);


        PriceDTO priceDTO = PriceDTO.builder().price(BigDecimal.TEN).brandId(brandId).productId(productId).currency(currency).startDate(LocalDateTime.of(2020, 6, 15, 0, 0)).endDate(LocalDateTime.of(2020, 6, 15, 11, 0)).build();

        when(priceService.getPriceInfoByProduct(brandId, productId, applicationDate)).thenReturn(priceDTO);

        ResponseEntity<PriceDTO> response = priceController.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(priceDTO, response.getBody());
        verify(priceService).getPriceInfoByProduct(brandId, productId, applicationDate);
    }

}

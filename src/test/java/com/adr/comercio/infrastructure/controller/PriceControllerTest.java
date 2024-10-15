package com.adr.comercio.application.controller;

import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.port.PricePort;
import com.adr.comercio.infrastructure.PriceController;
import com.comercio.application.dto.ErrorDTO;
import com.comercio.application.dto.PriceDTO;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PricePort pricePort;

    @Test
    void getPriceInfoByProduct_Success() {
        final int brandId = 1;
        final int productId = 35455;
        final String currency = "EUR";

        final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);


        final PriceDTO priceDTO = PriceDTO.builder().price(BigDecimal.TEN).brandId(brandId).productId(productId).currency(currency).startDate(LocalDateTime.of(2020, 6, 15, 0, 0)).endDate(LocalDateTime.of(2020, 6, 15, 11, 0)).build();

        when(pricePort.getPriceInfoByProduct(brandId, productId, applicationDate)).thenReturn(priceDTO);

        final ResponseEntity<PriceDTO> response = priceController.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(priceDTO, response.getBody());
        verify(pricePort).getPriceInfoByProduct(brandId, productId, applicationDate);
    }

    @Test
    void getPriceInfoByProduct_ThrowsPriceException_WhenNoPricesFound() {
        final int brandId = 1;
        final int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        when(pricePort.getPriceInfoByProduct(brandId, productId, applicationDate))
                .thenThrow(new PriceException(HttpStatus.NOT_FOUND,
                        ErrorDTO.builder().message("There is no product with these characteristics").build()));

        final PriceException exception = assertThrows(PriceException.class, () -> pricePort.getPriceInfoByProduct(brandId, productId, applicationDate));

        assertEquals(HttpStatus.NOT_FOUND, exception.getErroCode());
        assertEquals("There is no product with these characteristics", exception.getError().getMessage());

        verify(pricePort, times(1)).getPriceInfoByProduct(brandId, productId, applicationDate);
    }
}

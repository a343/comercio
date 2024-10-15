package com.adr.comercio.application.adapter;

import com.adr.comercio.application.adapater.PriceRestAdapter;
import com.adr.comercio.application.converter.PriceDTOConverter;
import com.adr.comercio.application.dto.Errors;
import com.adr.comercio.domain.exception.PriceException;
import com.adr.comercio.domain.model.Price;
import com.adr.comercio.domain.port.PriceService;
import com.comercio.application.dto.ErrorDTO;
import com.comercio.application.dto.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRestAdapterTest {

    @Mock
    private PriceDTOConverter priceConverter;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceRestAdapter priceRestAdapter;

    @Test
    void shouldReturnPriceDTOWhenServiceReturnsPrice() {
        final int brandId = 1;
        final int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        final Price price = new Price();
        final PriceDTO expectedPriceDTO = new PriceDTO();

        when(priceService.getPriceInfoByProduct(brandId, productId, applicationDate)).thenReturn(price);
        when(priceConverter.convert(price)).thenReturn(expectedPriceDTO);

        final PriceDTO result = priceRestAdapter.getPriceInfoByProduct(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(expectedPriceDTO, result);

        verify(priceService).getPriceInfoByProduct(brandId, productId, applicationDate);
        verify(priceConverter).convert(price);
        verify(priceService, times(1)).getPriceInfoByProduct(brandId, productId, applicationDate);
        verify(priceConverter, times(1)).convert(price);
    }

    @Test
    void getPriceInfoByProduct_ThrowsPriceException_WhenNoPricesFound() {
        final int brandId = 1;
        final int productId = 100;
        final LocalDateTime applicationDate = LocalDateTime.now();

        when(priceService.getPriceInfoByProduct(brandId, productId, applicationDate))
                .thenThrow(new PriceException(HttpStatus.NOT_FOUND,
                        ErrorDTO.builder().message(Errors.ERROR_NOT_FOUND).build()));

        final PriceException exception = assertThrows(PriceException.class, () -> priceService.getPriceInfoByProduct(brandId, productId, applicationDate));

        assertEquals(HttpStatus.NOT_FOUND, exception.getErroCode());
        assertEquals(Errors.ERROR_NOT_FOUND, exception.getError().getMessage());

        verify(priceService, times(1)).getPriceInfoByProduct(brandId, productId, applicationDate);
        verify(priceConverter, never()).convert(any());
    }

}

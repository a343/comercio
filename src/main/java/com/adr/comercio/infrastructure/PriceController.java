package com.adr.comercio.infrastructure;

import com.adr.comercio.domain.port.PricePort;
import com.comercio.application.dto.PriceDTO;
import com.comercio.infrastructure.controller.api.PriceApi;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
public class PriceController implements PriceApi {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PricePort priceOutputPort;

    @Override
    public ResponseEntity<PriceDTO> getPriceInfoByProduct(final Integer brandId, final Integer productId, final LocalDateTime applicationDate) {
        logger.info("getPriceInfoByProduct :: brandId {}, productId {}, applicationDate {}", brandId, productId, applicationDate);

        PriceDTO priceDTO = priceOutputPort.getPriceInfoByProduct(brandId, productId, applicationDate);
        return ResponseEntity.ok(priceDTO);
    }

}

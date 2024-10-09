package com.adr.comercio.infrastructure.controller;

import com.adr.comercio.domain.service.port.PriceService;
import com.comercio.aplicacion.dto.PriceDTO;
import com.comercio.infrastructure.api.PriceApi;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@AllArgsConstructor
@RestController
public class PriceController implements PriceApi {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PriceService productService;

    @Override
    public ResponseEntity<PriceDTO> getPriceInfoByProduct(String brandId, String productId, OffsetDateTime applicationDate) {

        logger.info("getPriceInfoByProduct :: branid {}, productId {}, applicationDate{}",brandId,productId,applicationDate);

        productService.getPriceInfoByProduct(brandId, productId, applicationDate);

        return ResponseEntity.ok(null);
    }
}

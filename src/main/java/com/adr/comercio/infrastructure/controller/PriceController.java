package com.adr.comercio.infrastructure.controller;

import com.adr.comercio.domain.service.port.PriceService;
import com.comercio.aplicacion.dto.PriceDTO;
import com.comercio.infrastructure.api.PriceApi;
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
    private final PriceService productService;

    @Override
    public ResponseEntity<PriceDTO> getPriceInfoByProduct(String brandId, String productId, LocalDateTime applicationDate) {
        logger.info("getPriceInfoByProduct :: brandId {}, productId {}, applicationDate {}", brandId, productId, applicationDate);

        PriceDTO priceDTO = productService.getPriceInfoByProduct(brandId, productId, applicationDate);
        return priceDTO != null ? ResponseEntity.ok(priceDTO) : ResponseEntity.noContent().build();
    }

}

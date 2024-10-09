package com.adr.comercio.domain.service.adapater;

import com.adr.comercio.domain.repository.PriceRepository;
import com.adr.comercio.domain.service.port.PriceService;
import com.comercio.aplicacion.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceDTO getPriceInfoByProduct(String brandId, String productId, OffsetDateTime applicationDate) {


        priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId,productId,applicationDate);

        return null;
    }
}

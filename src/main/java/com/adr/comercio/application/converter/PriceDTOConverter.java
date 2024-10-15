package com.adr.comercio.application.converter;

import com.adr.comercio.domain.model.Price;
import com.comercio.application.dto.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceDTOConverter {

    PriceDTO convert(Price price);

}

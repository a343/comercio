package com.adr.comercio.application.converters;

import com.adr.comercio.domain.model.Price;
import com.comercio.aplicacion.dto.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceConverter {

    PriceDTO convert(Price price);

}

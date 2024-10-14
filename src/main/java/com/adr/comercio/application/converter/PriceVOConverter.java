package com.adr.comercio.application.converter;

import com.adr.comercio.domain.model.PriceVO;
import com.comercio.application.dto.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceVOConverter {

    PriceDTO convert(PriceVO price);

}

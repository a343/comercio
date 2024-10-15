package com.adr.comercio.infrastructure.persistence.converter;

import com.adr.comercio.domain.model.Price;
import com.adr.comercio.infrastructure.persistence.model.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceConverter {

    List<Price> convert(List<PriceEntity> prices);

    Price convert(PriceEntity price);

}

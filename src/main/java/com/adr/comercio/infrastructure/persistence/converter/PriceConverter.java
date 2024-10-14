package com.adr.comercio.infrastructure.persistence.converter;

import com.adr.comercio.domain.model.PriceVO;
import com.adr.comercio.infrastructure.persistence.model.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceConverter {

    List<PriceVO> convert(List<PriceEntity> prices);

    PriceVO convert(PriceEntity price);

}

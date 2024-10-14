package com.adr.comercio.infrastructure.persistence.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "PRICES")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_id")
    @Schema(name = "productId", description = "Product id", example = "35455")
    private Integer productId;

    @NotNull
    @Schema(name = "brand_id", description = "Shop id", example = "1")
    @Size(min = 1)
    private Integer brandId;

    @Schema(name = "price_list",
            description = "Tariff id", example = "2")
    private Integer priceList;

    @Schema(name = "start_date",
            description = "Tariff start date",
            example = "2023-05-01T14:30:00Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(name = "end_date", description = "Tariff end date",
            example = "2023-05-01T14:30:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;


    @Schema(name = "priority", description = "Tariff priority", example = "1")
    private Long priority;

    @Column(name = "price")
    @Schema(name = "price", description = " Product price", example = "21.99")
    private BigDecimal price;

    @Schema(name = "currency", description = "Currency", example = "EUR")
    private String currency;
}

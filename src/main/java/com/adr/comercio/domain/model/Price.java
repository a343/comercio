package com.adr.comercio.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Table(name = "Product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_id")
    @Schema(name = "productId", description = "Identificador código de producto", example = "35455")
    private String productId;

    @NotNull
    @Schema(name = "brand_id", description = "Id que representa la empresa del grupo", example = "1")
    @Size(min = 1)
    private String brandId;

    @Schema(name = "price_list",
            description = " Identificador de la tarifa de precios " + "aplicable", example = "2")
    private Integer priceList;

    @Schema(name = "startDate",
            description = " Fecha en la que se empieza a aplicar aplicacion de la tarifa",
            example = "2023-05-01T14:30:00Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime startDate;

    @Schema(name = "endDate", description = " Fecha en la que se finaliza la aplicacion de la tarifa",
            example = "2023-05-01T14:30:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime endDate;


    @Schema(name = "priority", description = "prioridad de aplicacion de la tarifa", example = "1")
    private Long priority;

    @Column(name = "price")
    @Schema(name = "precio", description = " Precio del producto", example = "21.99")
    private Double precio;

    @Schema(name = "curr", description = " Moneda en la que se paga", example = "EUR")
    private String curr;
}

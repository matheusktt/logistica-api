package com.logistica.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logistica.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Client client;

    @Embedded
    private Addressee addressee;

    private BigDecimal tax;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime dateOrder;

    private OffsetDateTime dateFinished;
}
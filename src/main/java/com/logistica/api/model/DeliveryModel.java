package com.logistica.api.model;

import com.logistica.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryModel {

    private Long id;
    private String clientName;
    private AddresseeModel addressee;
    private BigDecimal tax;
    private DeliveryStatus deliveryStatus;
    private OffsetDateTime dateOrder;
    private OffsetDateTime dateFinished;
}
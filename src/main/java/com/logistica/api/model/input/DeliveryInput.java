package com.logistica.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {

    @Valid
    @NotNull
    private ClientIdInput client;

    @Valid
    @NotNull
    private AddresseeInput addressee;

    @NotNull
    private BigDecimal tax;
}
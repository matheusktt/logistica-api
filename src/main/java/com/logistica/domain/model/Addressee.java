package com.logistica.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class Addressee {

    @NotBlank
    @Column(name = "addressee_name")
    private String name;

    @NotBlank
    @Column(name = "addressee_address")
    private String address;

    @NotBlank
    @Column(name = "addressee_number")
    private String number;

    @NotBlank
    @Column(name = "addressee_complement")
    private String complement;

    @NotBlank
    @Column(name = "addressee_district")
    private String district;
}
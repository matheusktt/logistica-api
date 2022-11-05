package com.logistica.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OccurrenceModel {

    private Long id;
    private String description;
    private OffsetDateTime registrationDate;
}
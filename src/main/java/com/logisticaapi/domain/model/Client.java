package com.logisticaapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Client {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //verifica nullos e vazios
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email  //sintaxe de email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;
}
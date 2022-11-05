package com.logistica.api.exceptionhandler.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problems {

    private Integer status;
    private OffsetDateTime hour;
    private String title;
    private List<Fields> fields;

    @AllArgsConstructor
    @Getter
    public static class Fields {

        private String name;
        private String message;
    }
}

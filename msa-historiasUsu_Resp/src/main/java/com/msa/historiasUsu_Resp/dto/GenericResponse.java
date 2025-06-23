package com.msa.historiasUsu_Resp.dto;


import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GenericResponse<T> {

    private String message;
    private T data;

    public static <T> GenericResponse<T> of(T data, String message) {
        return GenericResponse.<T>builder().data(data).message(message).build();
    }

    public static <T> GenericResponse<T> of(T data) {
        return GenericResponse.<T>builder().data(data).build();
    }

    public static <T> GenericResponse<T> of(String message) {
        return GenericResponse.<T>builder().message(message).build();
    }
}

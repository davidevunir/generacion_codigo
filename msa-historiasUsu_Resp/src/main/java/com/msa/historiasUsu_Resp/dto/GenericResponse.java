package com.msa.historiasUsu_Resp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private String status;
    private String message;
    private T data;

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>("success", "Operación exitosa", data);
    }

    public static <T> GenericResponse<T> error(String message) {
        return new GenericResponse<>("error", message, null);
    }
}

package com.example.Task3.api;

import org.springframework.http.HttpStatus;

public class ApiResponseUtil {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", data);
    }

    public static <T> ApiResponse<T> error(HttpStatus httpStatus, String message) {
        return new ApiResponse<>(httpStatus.value(), message, null);
    }
}
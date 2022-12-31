package com.project.webapi.core.data;

public class ApiResponse<T> extends ApiBaseResponse {
    public T data;

    public ApiResponse(T data) {
        this.data = data;
        this.successfully = true;
    }
}

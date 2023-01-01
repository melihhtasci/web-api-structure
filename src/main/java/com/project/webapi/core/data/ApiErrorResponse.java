package com.project.webapi.core.data;

public class ApiErrorResponse extends ApiBaseResponse {

    public String message;

    public ApiErrorResponse(String message) {
        this.message = message;
        this.successfully = false;
    }
}

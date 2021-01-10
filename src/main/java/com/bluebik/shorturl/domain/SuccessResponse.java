package com.bluebik.shorturl.domain;

import lombok.Data;

@Data
public class SuccessResponse<T> {
    private String status;
    private int code;
    private T message;
    private T result;
    
    public SuccessResponse(String status, int code, T message, T result) {
        this.status = status;
        this.code = code;
        this.result = result;
        this.message = message;
    }
}


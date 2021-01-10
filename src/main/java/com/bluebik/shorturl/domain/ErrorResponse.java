package com.bluebik.shorturl.domain;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    
    private String status;
    private int code;
    private T message;
    
    public ErrorResponse() {
    }
    
}
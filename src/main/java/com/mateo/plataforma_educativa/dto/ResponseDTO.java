package com.mateo.plataforma_educativa.dto;

public class ResponseDTO<T> extends StatusDTO {
    private T result;

    public ResponseDTO(){}

    public ResponseDTO(T result, int status, String message) {
        super(status, message);
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

package com.epam.esm.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Response DTO
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(Object data) {
        this.data = data;
    }
}

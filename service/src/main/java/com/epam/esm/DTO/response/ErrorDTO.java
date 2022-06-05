package com.epam.esm.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Error DTO
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    private String errorMessage;
    private int errorCode;

    public ErrorDTO() {
    }

    public ErrorDTO(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

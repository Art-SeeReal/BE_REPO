package com.ArtSeeReal.pro.enums.module;

import com.ArtSeeReal.pro.enums.error.ErrorCode;
import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private String code;
    private int status;
    private String detail;

    public ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.detail = code.getDetail();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }
}
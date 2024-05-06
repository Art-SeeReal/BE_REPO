package com.ArtSeeReal.pro.enums.error;

import com.ArtSeeReal.pro.enums.module.EnumModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode implements EnumModel {

    // COMMON
    TEMPORARY_SERVER_ERROR(400,"C000","에러 입니다."),
    INVALID_CODE(400, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(204, "C002", "Resource not found"),
    EXPIRED_CODE(400, "C003", "Expired Code"),
    NOT_IMPLEMENTED_EXCEPTION(400, "C004", "아직 구현이 안되었습니다."),
    EMAIL_NOT_EQUAL_ERROR(400,"C005","[ERROR] 이메일이 존재하지 않습니다."),
    NAME_NOT_EQUAL_ERROR(400,"C005","[ERROR] 이름이 존재하지 않습니다."),
    ID_NOT_EQUAL_ERROR(400,"C007","[ERROR] 주어진 이메일의 아이디와 제공된 아이디가 다릅니다."),
    ID_NOT_FOUND(500,"C008","[ERROR] 아이디가 존재하지 않습니다."),
    NO_DATA_ERROR(500,"C009","[ERROR] 데이터가 존재하지 않습니다."),
    NO_TOKEN_ERROR(500,"C010","[ERROR] 토큰이 존재하지 않습니다."),
    NO_AUTH_STR_ERROR(500,"C011","[ERROR] 인증 문자열이 존재하지 않습니다."),
    EXPIRED_TOKEN_ERROR(500,"C012","[ERROR] 토큰의 유효기간이 지났습니다."),
    NO_REFRESH_TOKEN_ERROR(500,"C013","[ERROR] 리프레쉬 토큰이 존재하지 않습니다."),
    NO_TYPE_REFRESH_TOKEN_ERROR(500,"C014","[ERROR] 리프레쉬 토큰 타입이 아닙니다."),
    NOT_EXISTS_REFRESH_TOKEN_ERROR(500,"C015","[ERROR] DB에 리프레쉬 토큰이 없습니다."),;

    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }

}

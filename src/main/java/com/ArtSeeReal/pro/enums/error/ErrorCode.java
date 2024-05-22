package com.ArtSeeReal.pro.enums.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

    // COMMON
    TEMPORARY_SERVER_ERROR(500,"C000","에러 입니다."),
    INVALID_CODE(400, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(404, "C002", "Resource not found"),
    EMAIL_NOT_EQUAL_ERROR(400,"C003","[ERROR] 이메일이 존재하지 않습니다."),
    NAME_NOT_EQUAL_ERROR(400,"C004","[ERROR] 이름이 존재하지 않습니다."),
    ID_NOT_EQUAL_ERROR(400,"C005","[ERROR] 주어진 이메일의 아이디와 제공된 아이디가 다릅니다."),
    ID_NOT_FOUND(404,"C006","[ERROR] 아이디가 존재하지 않습니다."),
    NO_DATA_ERROR(404,"C007","[ERROR] 데이터가 존재하지 않습니다."),
    NO_TOKEN_ERROR(401,"C008","[ERROR] 토큰이 존재하지 않습니다."),
    NO_AUTH_STR_ERROR(401,"C009","[ERROR] 인증 문자열이 존재하지 않습니다."),
    NO_USER_DATA_ERROR(404,"C010","[ERROR] 유저 데이터가 없습니다."),
    ID_DUPLICATE_ERROR(409,"C011","[ERROR] 아이디가 중복되었습니다."),
    NICKNAME_DUPLICATE_ERROR(409,"C012","[ERROR] 닉네임이 중복되었습니다."),
    EMAIL_DUPLICATE_ERROR(409,"C013","[ERROR] 이메일이 중복되었습니다."),
    NO_BOARD_DATA_ERROR(404,"C014","[ERROR] 게시물 데이터가 없습니다."),
    NO_PAGE_ERROR(400,"C015","[ERROR] 페이지가 1미만 또는 NULL값입니다."),
    NOT_IMPLEMENTED_EXCEPTION(501, "C016", "아직 구현이 안되었습니다."),
    EXPIRED_TOKEN_ERROR(401,"C017","[ERROR] 토큰의 유효기간이 지났습니다."),
    NO_REFRESH_TOKEN_ERROR(401,"C018","[ERROR] 리프레쉬 토큰이 존재하지 않습니다."),
    NO_ACCESS_TOKEN_ERROR(401,"C019","[ERROR] 엑세스 토큰이 존재하지 않습니다."),
    NO_TYPE_REFRESH_TOKEN_ERROR(401,"C020","[ERROR] 리프레쉬 토큰 타입이 아닙니다."),
    NO_AUTHORITY_ERROR(403,"C021","[ERROR] 권한이 없습니다."),
    NOT_EXISTS_REFRESH_TOKEN_ERROR(404,"C022","[ERROR] DB에 리프레쉬 토큰이 없습니다."),;


    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}

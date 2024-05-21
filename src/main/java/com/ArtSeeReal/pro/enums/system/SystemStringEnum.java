package com.ArtSeeReal.pro.enums.system;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public enum SystemStringEnum {

    EMAIL_TITLE("ArtSeeReal의 인증 이메일입니다."),
    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    MASKED_CHARACTERS("*"),;
    private final String text;
    SystemStringEnum(String text){
        this.text = text;
    }

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.getText().length());
            char randomChar = CHARACTERS.getText().charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}

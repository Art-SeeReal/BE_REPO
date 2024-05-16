package com.ArtSeeReal.pro.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface MailService {

    // TODO : UserService 및 ServiceImpl 분리 할 때 나누자

    String findId(String name,String email);
    void authForPassword(String name,String email,String id) throws IOException, MessagingException;
    String authCreateToken(String authRandomStr);
    void changePassword(String token, String password);
    void removeExpired();

}

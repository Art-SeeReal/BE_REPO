package com.ArtSeeReal.pro.service;

public interface ValidateService {
    boolean adminCheck(String userUid);
    boolean identification(String userUid, String targetUid);
    void roleCheck(String userUid, String targetUid);
    void existsUser(String userUid);
}

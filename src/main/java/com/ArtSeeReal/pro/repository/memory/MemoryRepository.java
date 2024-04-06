package com.ArtSeeReal.pro.repository.memory;

public interface MemoryRepository {
    String saveAuthStr(String userUid, String authStr);
    boolean existAuthStr(String authStr);
    void deleteAuthStr(String authStr);
    String findByUserUidFromAuthNumbers(String authStr);
    String saveToken(String userUid, String token);
    boolean existToken(String token);
    void deleteToken(String token);
    String findByUserUidFromTokens(String token);
    void removeExpiredAuthNumbers();
    void removeExpiredTokens();

}

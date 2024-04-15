package com.ArtSeeReal.pro.repository.memoryimpl;

import com.ArtSeeReal.pro.etc.UserAuthVO;
import com.ArtSeeReal.pro.repository.memory.MemoryRepository;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryRepositoryImpl implements MemoryRepository {
    private final Map<String, UserAuthVO> authNumbers;
    private final Map<String, UserAuthVO> tokens;


    public MemoryRepositoryImpl() {
        this.tokens = new ConcurrentHashMap<>();
        this.authNumbers = new ConcurrentHashMap<>();
    }

    @Override
    public String saveAuthStr(String userUid, String authStr) {
        if (authStr == null)
            throw new IllegalArgumentException("authStr cannot be null");
        if (authNumbers.containsKey(authStr))
            throw new IllegalArgumentException("authStr already exists");
        authNumbers.put(authStr, new UserAuthVO(userUid));
            return authStr;
    }

    @Override
    public boolean existAuthStr(String authStr) {
        if (authStr == null)
            throw new IllegalArgumentException("authStr cannot be null");
        return authNumbers.containsKey(authStr);
    }

    @Override
    public void deleteAuthStr(String authStr) {
        if (authStr == null)
            throw new IllegalArgumentException("authStr cannot be null");
        authNumbers.remove(authStr);
    }

    @Override // TODO : 메서드 이름 잘못된듯 authNumbers 에서 찾는건데 이런 이름은 전체에서 뒤지는 느낌임
    public String findByUserUidFromAuthNumbers(String authStr) {
        return authNumbers.get(authStr).getUserUid();
    }

    @Override
    public String saveToken(String userUid, String token) {
        if (token == null)
            throw new IllegalArgumentException("token cannot be null");
        if (tokens.containsKey(token))
            throw new IllegalArgumentException("token already exists");
        tokens.put(token, new UserAuthVO(userUid));
        return token;
    }

    @Override
    public boolean existToken(String token) {
        if (token == null)
            throw new IllegalArgumentException("token cannot be null");
        return tokens.containsKey(token);
    }

    @Override
    public void deleteToken(String token) {
        if (token == null)
            throw new IllegalArgumentException("token cannot be null");
        tokens.remove(token);
    }

    @Override
    public String findByUserUidFromTokens(String token) {
        return tokens.get(token).getUserUid();
    }

    @Override
    public String findAuthStr() {
        return authNumbers.keySet()
                .iterator()
                .next();
    }

    @Override
    public String findTokens() {
        return tokens.keySet()
                .iterator()
                .next();
    }

    @Override
    public void removeExpiredAuthNumbers() {
        LocalDateTime now = LocalDateTime.now();
        Iterator<Entry<String, UserAuthVO>> iterator = authNumbers.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, UserAuthVO> entry = iterator.next();
            LocalDateTime expirationTime = entry.getValue().getAuthTime();
            if (expirationTime.isBefore(now))
                iterator.remove();
        }
    }

    @Override
    public void removeExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        Iterator<Entry<String, UserAuthVO>> iterator = tokens.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, UserAuthVO> entry = iterator.next();
            LocalDateTime expirationTime = entry.getValue().getAuthTime();
            if (expirationTime.isBefore(now))
                iterator.remove();
        }
    }
}

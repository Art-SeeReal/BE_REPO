package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String USERNAME_CLAIM = "username";
    private static final String ROLE_CLAIM = "role";
    private static final String CATEGORY_CLAIM = "category";
    private static final String BEARER_PREFIX = "Bearer ";

    private final SecretKey secretKey;
    private final UserRepository userRepository;

    public TokenServiceImpl(@Value("${spring.jwt.secret}") String secret, UserRepository userRepository) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        this.userRepository = userRepository;
    }

    private JwtParser getJwtParser() {
        return Jwts.parser().verifyWith(secretKey).build();
    }

    private JwtBuilder getJwtBuilder() {
        return Jwts.builder().signWith(secretKey);
    }

    private String getClaim(String token, String claimName) {
        return getJwtParser()
                .parseSignedClaims(token)
                .getPayload()
                .get(claimName, String.class);
    }

    public String getUsername(String token) {
        return getClaim(token, USERNAME_CLAIM);
    }

    @Override
    public String getRole(String token) {
        return getClaim(token, ROLE_CLAIM);
    }

    @Override
    public String getCategory(String token) {
        return getClaim(token, CATEGORY_CLAIM);
    }

    public Boolean isExpired(String token) {
        return getJwtParser()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(String category, String username, String role, Long expiredMs) {
        return getJwtBuilder()
                .claim(CATEGORY_CLAIM, category)
                .claim(USERNAME_CLAIM, username)
                .claim(ROLE_CLAIM, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .compact();
    }

    @Override
    public String getTokenInCookie(HttpServletRequest request, String category) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(category)) {
                    return cookie.getValue();
                }
            }
        }
        throw new NullPointerException(ErrorCode.NO_REFRESH_TOKEN_ERROR.getMessage());
    }

    @Override
    public void tokenNullCheck(String token) {
        if (token == null) {
            throw new NullPointerException(ErrorCode.NO_TOKEN_ERROR.getMessage());
        }
    }

    @Override
    public void expiredCheck(String token) {
        if (isExpired(token)) {
            throw new IllegalArgumentException(ErrorCode.EXPIRED_TOKEN_ERROR.getMessage());
        }
    }

    @Override
    public String getUserId(String token) {
        return getUsername(token);
    }

    @Override
    public boolean checkTokenCategory(String token, String category) {
        return getCategory(token).equals(category);
    }

    @Override
    public String createToken(String category, String userId, String role, Long time) {
        return createJwt(category, userId, role, time);
    }

    @Override
    public String getUserUid(String token) {
        String accessToken = token.startsWith(BEARER_PREFIX) ? token.substring(BEARER_PREFIX.length()) : token;
        return userRepository.findByUserId(getUserId(accessToken))
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NO_USER_DATA_ERROR.getCode()))
                .getUid();
    }
}

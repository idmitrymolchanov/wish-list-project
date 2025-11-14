package ru.newgor.wishlist.usecase.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.domain.UserModel;
import ru.newgor.wishlist.domain.exception.JwtTokenExpiredException;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "my-very-secret-key-that-should-be-long-enough-to-do";
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 365;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Генерация токена для пользователя
     */
    public String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Извлечь логин из токена
     */
    public String extractLogin(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Проверить, что токен не истёк и принадлежит пользователю
     */
    public String isTokenValid(String token) {
        final String login = extractLogin(token);
        if(isTokenExpired(token)) {
            throw new JwtTokenExpiredException("kmnkknk");
        }
        return login;
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.substring(7))
                .getBody();
    }
}

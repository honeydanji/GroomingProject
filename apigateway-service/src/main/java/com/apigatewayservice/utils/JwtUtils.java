package com.apigatewayservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

    private final Environment environment;

    public boolean isJwtValid(String jwt) {
        boolean returnValue = true;

        String subject = null;

        try {
            Claims claims = parseJwt(jwt);
            subject = claims.getSubject();
        } catch (Exception e) {
            log.error(e.getMessage());
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    public Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret").getBytes())
                .parseClaimsJws(jwt)
                .getBody();

        log.info(environment.getProperty("token.secret"));
        return claims;
    }
}

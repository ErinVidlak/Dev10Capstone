package com.dev10.userapi.utility;

import com.dev10.userapi.models.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String ISSUER = "dev10-users-api";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(AppUser user) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .claim("roles", String.join(",", user.getRoles()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public AppUser getUserFromToken(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            String username = jws.getBody().getSubject();

            Claims claims = jws.getBody();
            String id = (String)claims.get("id");
            String rolesString = (String)claims.get("roles");
            String[] roles = rolesString.split(",");

            return new AppUser(id, username, null, false, roles);

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }
}
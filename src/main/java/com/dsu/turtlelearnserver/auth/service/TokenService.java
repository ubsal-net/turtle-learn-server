package com.dsu.turtlelearnserver.auth.service;

import com.dsu.turtlelearnserver.auth.config.JwtProperties;
import com.dsu.turtlelearnserver.auth.exception.UnknownTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static final String AUTHORITIES_KEY = "authorities";
    private final JwtProperties jwtProperties;
    private final Key key;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String authoritiesStr = claims.get(AUTHORITIES_KEY).toString()
            .replaceAll("[\\[\\]\\s]", "");
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(authoritiesStr.split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        var user = new org.springframework.security.core.userdetails.User(claims.getSubject(), "",
            authorities);

        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public String generateAccessToken(Authentication authentication) {
        if (authentication.getAuthorities().isEmpty()) {
            throw new UnknownTokenException("유효하지 않은 토큰입니다.");
        }
        Set<String> authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority).collect(
                Collectors.toSet());
        Date now = new Date();
        Date expirationDate = new Date(
            now.getTime() + jwtProperties.getAccessTokenExpiration());

        return Jwts.builder()
            .setSubject(authentication.getName())
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public void validToken(String token) {
        getClaims(token);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}

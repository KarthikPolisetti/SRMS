package com.srms.student_result_management.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil
{

private  final String SECRET_STRING="MySuperSecretKeyThatIsAtLeast32BytesLongForSecurity12345";
private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET_STRING.getBytes());


public String generateToken(String username,String role,Long id){
    return Jwts.builder()
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
            .signWith(secretKey)
            .claim("role",role)
            .claim("userId",id)
            .compact();
}



public String extractUsername(String token){
    return getClaims(token).getSubject();
}


public boolean isTokenValid(String token,String userName){
    final String extractedUsername=extractUsername(token);
    return (extractedUsername.equals(userName) && !isTokenExpired(token));
}

private boolean isTokenExpired(String token){
    return  getClaims(token).getExpiration().before(new Date());
}


    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

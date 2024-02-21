package com.backend.velanmai.Service;

import com.backend.velanmai.DTO.RegisterDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private static final long EXPIRATION_TIME=86400000;
    private static final String SECRET="drsdfaaszxvgfdgdbfd785275227527852752752725785278527huioyit7ue5f16s8f16s8f16sf14efse68f4sf";

    SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String tokenGenerater(RegisterDto authDto)
    {
        return Jwts.builder()
                .subject(authDto.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction)
    {
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token)
    {
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }


}

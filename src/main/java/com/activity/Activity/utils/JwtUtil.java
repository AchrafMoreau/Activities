package com.activity.Activity.utils;

import com.activity.Activity.service.UserDetailsImpl;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyPairGeneratorSpi;
import java.security.Signature;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        logger.info("EXP {}" ,new Date(new Date().getTime() + expirationTime));

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTime))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String parserEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        logger.info("exprition time {} ", expirationTime);
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid Jwt Token {} ", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired Jwt Token {} ", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("Jwt Token is Unsupported {} ", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("No Token Was Provided {} ", e.getMessage());
        }

        return false;
    }

}

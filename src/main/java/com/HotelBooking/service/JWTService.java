package com.HotelBooking.service;

import com.HotelBooking.entity.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {

    @Value("${jwt.algorithms.key}")
    private String algorithmKey;

    @Value("${jwt.issue}")
    private String issuer; // taki pata chale token kis ne issue kiya hai

    @Value("${jwt.expiryDuration}")
    private int expiryTime;

    private Algorithm algorithm;

    private final static String USER_NAME = "username";


    @PostConstruct
    public void postConstruct() // post construct run automatically
    {
        algorithm  =  Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser user)
    {
        // how to generate token

       return JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

        public String getUsername(String token)
        {
           DecodedJWT decodedjwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token); // this will help me to decode the token.
            return decodedjwt.getClaim(USER_NAME).asString();
        }

}

// withClaim :- claim is put your username in payload
// algorithm consist two things.. 1. algorithm 2. security

/* getClaim :- getclaim is built in method which read the USER_NAME from decoded JWT token and getclaim return type
    is not strinq by appling asString that USER_NAME converted to string
 */
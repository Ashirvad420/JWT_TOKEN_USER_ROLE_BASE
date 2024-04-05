package com.HotelBooking.service;

import com.HotelBooking.entity.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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


    @PostConstruct
    public void postConstruct() // post construct run automatically
    {
//        System.out.println(algorithmKey);
//        System.out.println(issuer);
//        System.out.println(expiryTime);

        algorithm  =  Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser user)
    {
        // how to generate token

       return JWT.create()
                .withClaim("user",user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

}

// withClaim :- claim is put your username in payload
// algorithm consist two things.. 1. algorithm 2. security
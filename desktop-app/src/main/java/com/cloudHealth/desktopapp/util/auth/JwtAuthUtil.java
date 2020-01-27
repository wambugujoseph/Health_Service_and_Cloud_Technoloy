package com.cloudHealth.desktopapp.util.auth;

import com.cloudHealth.desktopapp.model.UserDetails;
import io.jsonwebtoken.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.cglib.core.internal.Function;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 1/18/2020
 * Time: 2:38 PM
 * Project: desktop-app
 */

public class JwtAuthUtil {

    private static boolean tokenExpired = true;

    public String getPublicKey(){
        Resource resource = new ClassPathResource("public.txt");
        String publicKey;
        try {
            publicKey = IOUtils.toString(resource.getInputStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        try {
           Claims claims = Jwts.parser().setSigningKey(getPublicKeyFromString(getPublicKey())).parseClaimsJws(token).getBody();
           tokenExpired=false;
           return claims;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            //System.out.println(e.getMessage());
            tokenExpired = true;
        }
        return null;
    }

    public  Boolean isTokenExpired(String token){
        Claims claims = extractAllClaims(token);
        return tokenExpired;
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsrename());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.RS256, getPublicKey()).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsrename())) && !isTokenExpired(token);
    }

    public static RSAPublicKey getPublicKeyFromString(String key) {
        try {
            String publicKeyPEM = key;
            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "");
            publicKeyPEM =publicKeyPEM.trim();
            publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
            byte[] encoded = Base64.decodeBase64(publicKeyPEM);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

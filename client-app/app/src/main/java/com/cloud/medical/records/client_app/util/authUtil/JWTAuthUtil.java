package com.cloud.medical.records.client_app.util.authUtil;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import com.cloud.medical.records.client_app.model.UserDetails;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthUtil {

    private static boolean tokenExpired = true;
    public static String PUBLIC_KEY;

    public  static String JWT_ACCESS_TOKEN_OBJECT;

    public JWTAuthUtil(String publicKey) {
        this.PUBLIC_KEY = publicKey;
    }

    public JWTAuthUtil() {
    }

    public static String getPublicKey(){
        return PUBLIC_KEY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public static Claims extractAllClaims(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(getPublicKeyFromString(getPublicKey())).parseClaimsJws(token).getBody();
            tokenExpired=false;
            return claims;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
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
            byte[] encoded = Base64.decode(publicKeyPEM,Base64.DEFAULT);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

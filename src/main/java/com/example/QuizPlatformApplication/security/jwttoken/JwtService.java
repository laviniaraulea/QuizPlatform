package com.example.QuizPlatformApplication.security.jwttoken;

import com.example.QuizPlatformApplication.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


        @Value("${token.signing.key}")
        private String jwtSigningKey;

        public String extractUserName(String token) {
            return extractClaim(token, Claims::getSubject);
        }


        public String extractUserEmail(String token) {
            return extractClaim(token, "email");
        }


        private <T> T extractClaim(String token, String claimName) {
            final Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSigningKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return (T) claims.get(claimName);
        }

        public String generateToken(User userDetails) {
            return generateToken(new HashMap<>(), userDetails);
        }


        public boolean isTokenValid(String token, User user) {
            final String userName = extractUserName(token);
            return (userName.equals(user.getUsername())) && !isTokenExpired(token);
        }


        public boolean validateToken(String token) {
            return false;
        }

        private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
            final Claims claims = extractAllClaims(token);
            return claimsResolvers.apply(claims);
        }

        private String generateToken(Map<String, Object> extraClaims, User user) {

            extraClaims.put("username", user.getUsername());
            return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 3000 * 60 *6))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                    .getBody();
        }

        private Key getSigningKey() {
            byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }

}

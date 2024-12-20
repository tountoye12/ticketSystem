package edu.miu.ticket_system.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final Key signingKey;
    private final int tokenValidity;

    public JwtUtil(@Value("${jwt.secret-key}") String secretKey,
                   @Value("${jwt.token-validity}") int tokenValidity) {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.tokenValidity = tokenValidity;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getDecoder().decode(parts[1]));
        return new Claims(payload);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        Date issuedAt = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + tokenValidity * 1000L);

        Map<String, Object> header = new HashMap<>();
        header.put("alg", SignatureAlgorithm.HS256.getValue());
        header.put("typ", "JWT");

        String encodedHeader = Base64.getUrlEncoder().encodeToString(header.toString().getBytes());
        String encodedPayload = Base64.getUrlEncoder().encodeToString(claims.toString().getBytes());
        String signature = createSignature(encodedHeader, encodedPayload);

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    private String createSignature(String encodedHeader, String encodedPayload) {
        String data = encodedHeader + "." + encodedPayload;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] bytes = mac.doFinal(data.getBytes());
            return Base64.getUrlEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JWT signature", e);
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    // Custom Claims class to handle payload extraction (for simplicity)
    private static class Claims {
        @Getter
        private String subject;
        @Getter
        private Date expiration;
        private Map<String, Object> claims;

        public Claims(String payload) {
            this.claims = new HashMap<>();
            this.claims.put("subject", payload);
            this.expiration = new Date(System.currentTimeMillis() + 3600 * 1000L);
        }

    }
}

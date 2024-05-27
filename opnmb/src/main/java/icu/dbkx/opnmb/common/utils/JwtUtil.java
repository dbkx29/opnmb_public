package icu.dbkx.opnmb.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {
    private final static String secretKey = "T3BjaGF0X0lzX1RIRV9CRVNUX+iBiuWkqV/ova/ku7ZfZXZlcg==";

    private final static long ttl = 864000;

    /**
     * 生成JWT
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttl * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setClaims(claims)
                .compact();
    }
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

}

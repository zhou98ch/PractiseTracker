package Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

// This implementation is referenced from online resource
// TODO read this code later to figure out what it does
public class JwtUtil {
    /**
     * generate jwt token
     * use Hs256 algorithm
     *
     * @param secretKey
     * @param ttlMillis
     * @param claims
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * decode Token
     *
     * @param secretKey
     * @param token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
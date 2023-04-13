package kr.co.voard.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final long TOKEN_VALIDATE_TIME = 1000 * 60 * 60 * 1; // 1시간
	private SecretKey secretKey;
	
	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	// JWT 클레임 추출
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(secretKey)
							.build()
							.parseClaimsJws(token)
							.getBody();
		
		return claimsResolver.apply(claims);
	}
	
	// 사용자 ID 추출
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// 토큰 유효성 검증
	public boolean validateToken(String token) {
		Date expiration = getClaimFromToken(token, Claims::getExpiration);
		return !expiration.before(new Date());
	}
	
	// HTTP 헤더에서 토큰 추출
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	// JWT 생성
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		
		Date createDate = new Date();
		Date expireDate = new Date(createDate.getTime() + TOKEN_VALIDATE_TIME);
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(createDate)
				.setExpiration(expireDate)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
				
	}
	
}

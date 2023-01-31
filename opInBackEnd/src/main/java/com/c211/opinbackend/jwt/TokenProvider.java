package com.c211.opinbackend.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.c211.opinbackend.auth.model.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

	// private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private final String secret;
	private final long accessTokenValidityInMilliseconds;
	private final long refreshTokenValidityInMilliseconds;

	private Key key;

	public TokenProvider(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
		@Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {
		this.secret = secret;
		this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds;
		this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds;
	}

	/*
	 * 시크릿 키 설정
	 */
	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	/*
	 * 검증된 이메일에 대해 토큰을 생성하는 메서드
	 * AccessToken의 Claim으로는 email과 nickname을 넣습니다.
	 */
	public TokenDto createToken(String email,
		String authorities) {
		long now = (new Date()).getTime();
		// UserBase user = userRepository.findByEmail(email) // princial.toSTring()
		// 	.orElseThrow(() -> new UserNotFoundException("해당하는 이메일이 존재하지 않습니다."));

		String accessToken = Jwts.builder()
			.claim("email", email)
			// .claim("nickname", user.getNickname())
			.claim(AUTHORITIES_KEY, authorities)
			.setExpiration(new Date(now + accessTokenValidityInMilliseconds))
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();

		String refreshToken = Jwts.builder()
			.claim(AUTHORITIES_KEY, authorities)
			.claim("email", email)
			// .claim("nickname", user.getNickname())
			.setExpiration(new Date(now + refreshTokenValidityInMilliseconds))
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();

		return new TokenDto(accessToken, refreshToken);
	}

	/*
	 * 권한 가져오는 메서드
	 */
	public Authentication getAuthentication(String token) {
		Claims claims = getClaims(token);

		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(claims.get("email"), null, authorities);
	}

	/*
	 * 토큰 유효성 검사하는 메서드
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원하지 않는 JWT토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT토큰이 잘못되었습니다.");
		}
		return false;
	}

	/*
	 * 토큰에서 Claim 추츨하는 메서드
	 */
	public Claims getClaims(String token) {
		try {
			return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}

package kr.co.voard.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.voard.entity.UserEntity;
import kr.co.voard.jwt.JwtUtil;
import kr.co.voard.repository.UserRepo;
import kr.co.voard.security.MyUserDetails;
import kr.co.voard.security.SecurityUserService;
import kr.co.voard.service.UserService;
import kr.co.voard.vo.TermsVO;
import kr.co.voard.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
public class UserController {
	
	private AuthenticationManager authenticationManager;
	private SecurityUserService securityUserService;
	private JwtUtil jwtUtil;
	private UserService service;
	private UserRepo repo;
	
	@GetMapping("/user/terms")
	public TermsVO terms() {
		return service.selectTerms();
	}
	
	@PostMapping("/user/register")
	public void register(@RequestBody UserVO vo) {
		service.insertUser(vo);
	}
	
	@GetMapping("/user")
	public UserEntity user(Authentication authentication) {
		
		log.info("user1...");
		MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
		
		return myUserDetails.getUser();
	}
	
	
	@PostMapping("/user/login")
	public Map<String, Object> login(@RequestBody UserVO vo) {
		
		log.info("vo : " + vo);
		
		String uid = vo.getUid();
		String pass = vo.getPass();
		
		log.info("login...1");
		
		// Security 인증처리
		MyUserDetails myUserDetails = securityUserService.loadUserByUsername(uid);
		log.info("login...2");
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUserDetails, pass));
		log.info("login...3");
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// JWT 생성
		String token = jwtUtil.generateToken(uid);
		log.info("login...4" + token);
		
		// JWT 출력
		UserEntity user = myUserDetails.getUser();
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("accessToken", token);
		resultMap.put("user", user);
		
		return resultMap;
	
	}
	
	public void logout() {
		
	}
}

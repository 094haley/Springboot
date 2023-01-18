package kr.co.sboard.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {
	
	@GetMapping("list")
	public String list(@AuthenticationPrincipal MyUserDetails myUser, Model model) {
		
		UserEntity user = myUser.getUser();
		model.addAttribute("user", user);
		
		return "list";
	}
	
	@GetMapping("modify")
	public String modify() {
		return "modify";
	}
	
	@GetMapping("view")
	public String view() {
		return "view";
	}
	
	@GetMapping("write")
	public String write() {
		return "write";
	}
	
}

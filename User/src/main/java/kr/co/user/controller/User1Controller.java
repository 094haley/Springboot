package kr.co.user.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.user.service.User1Service;
import kr.co.user.vo.User1VO;

@Controller
@RequestMapping("/user1")
public class User1Controller {
	
	@Autowired
	private User1Service service;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<User1VO> users = service.selectUser1s();
		model.addAttribute("users", users);
		
		return "/user1/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/user1/register";
	}
	
	@PostMapping("/register")
	public String register(User1VO vo) {
		service.insertUser1(vo);
		return "redirect:/user1/list";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, String uid) {
		User1VO user = service.selectUser1(uid);
		model.addAttribute("user", user);
		return "/user1/modify";
	}
	
	@PostMapping("/modify")
	public String modify(User1VO vo) {
		service.updateUser1(vo);
		return "redirect:/user1/list";
	}
	
	@GetMapping("/delete")
	public String delete(String uid) {
		service.deleteUser1(uid);
		return "redirect:/user1/list";
	}

}

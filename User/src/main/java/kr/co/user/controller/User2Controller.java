package kr.co.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.user.service.User2Service;
import kr.co.user.vo.User2VO;

@Controller
@RequestMapping("/user2")
public class User2Controller {
	
	@Autowired
	private User2Service service;

	@GetMapping("/list")
	public String list(Model model) {
		
		List<User2VO> users = service.selectUser2s();
		model.addAttribute("users", users);
		
		return "/user2/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/user2/register";
	}
	
	@PostMapping("/register")
	public String register(User2VO vo) {
		service.insertUser2(vo);
		return "redirect:/user2/list";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, String uid) {
		User2VO user = service.selectUser2(uid);
		model.addAttribute("user", user);
		return "/user2/modify";
	}
	
	@PostMapping("/modify")
	public String modify(User2VO vo) {
		service.updateUser2(vo);
		return "redirect:/user2/list";
	}
	
	@GetMapping("/delete")
	public String delete(String uid) {
		service.deleteUser2(uid);
		return "redirect:/user2/list";
	}
	
}

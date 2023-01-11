package kr.co.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.user.service.User4Service;
import kr.co.user.vo.User4VO;

@Controller
@RequestMapping("/user4")
public class User4Controller {

	@Autowired
	private User4Service service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User4VO> users = service.selectUser4s();
		model.addAttribute("users", users);
		return "/user4/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/user4/register";
	}
	
	@PostMapping("/register")
	public String register(User4VO vo) {
		service.insertUser4(vo);
		return "redirect:/user4/list";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, int seq) {
		User4VO user = service.selectUser4(seq);
		model.addAttribute("user", user);
		return "/user4/modify";
	}
	
	@PostMapping("/modify")
	public String modify(User4VO vo) {
		service.updateUser4(vo);
		return "redirect:/user4/list";
	}
	
	@GetMapping("/delete")
	public String delete(int seq) {
		service.deleteUser4(seq);
		return "redirect:/user4/list";
	}
	
}

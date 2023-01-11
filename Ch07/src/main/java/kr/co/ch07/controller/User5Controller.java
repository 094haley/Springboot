package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ch07.service.User5Service;
import kr.co.ch07.vo.User5VO;


@Controller
@RequestMapping("/user5")
public class User5Controller {
	
	@Autowired
	private User5Service service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User5VO> users = service.selectUser5s();
		model.addAttribute("users", users);
		return "/user5/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/user5/register";
	}
	
	@PostMapping("/register")
	public String register(User5VO vo) {
		service.insertUser5(vo);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, String uid) {
		User5VO user = service.selectUser5(uid);
		model.addAttribute("user", user);
		return "/user5/modify";
	}
	
	@PostMapping("/modify")
	public String modify(User5VO vo) {
		service.updateUser5(vo);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/delete")
	public String delete(String uid) {
		service.deleteUser5(uid);
		return "redirect:/user5/list";
	}

}

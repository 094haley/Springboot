package kr.co.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.user.service.User3Service;

@Controller
@RequestMapping("/user3")
public class User3Controller {
	
	@Autowired
	private User3Service service;
	
	
}

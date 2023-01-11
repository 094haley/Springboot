package kr.co.ch07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.ch07.repository.User1Repo;
import kr.co.ch07.vo.User1VO;

@Controller
public class Maincontroller {
	
	@Autowired
	private User1Repo repo;

	@GetMapping(value={"/", "/index"})
	public String index() {
		return "/index";
	}
	
	@GetMapping("/query1")
	public String query1() {
		User1VO vo = repo.findUser1VOByUid("a101");
		System.out.println("query1 결과 : " + vo);
		return "redirect:/";
	}
	
	@GetMapping("/query2")
	public String query2() {
		List<User1VO> users = repo.findUser1VOByName("김유신");
		System.out.println("query2 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query3")
	public String query3() {
		List<User1VO> users = repo.findUser1VOByNameNot("김유신");
		System.out.println("query3 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query4")
	public String query4() {
		User1VO vo = repo.findUser1VOByUidAndName("a101", "김유신");
		System.out.println("query4 결과 : " + vo);
		return "redirect:/";
	}
	
	@GetMapping("/query5")
	public String query5() {
		List<User1VO> users = repo.findUser1VOByUidOrName("a101", "김춘추");
		System.out.println("query5 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query6")
	public String query6() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThan(25);
		System.out.println("query6 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query7")
	public String query7() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThanEqual(25);
		System.out.println("query7 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query8")
	public String query8() {
		List<User1VO> users = repo.findUser1VOByAgeLessThan(33);
		System.out.println("query8 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query9")
	public String query9() {
		List<User1VO> users = repo.findUser1VOByAgeLessThanEqual(33);
		System.out.println("query9 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query10")
	public String query10() {
		List<User1VO> users = repo.findUser1VOByNameLike("김%");
		System.out.println("query10 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query11")
	public String query11() {
		List<User1VO> users = repo.findUser1VOByNameContains("신");
		System.out.println("query11 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query12")
	public String query12() {
		List<User1VO> users = repo.findUser1VOByNameStartsWith("이");
		System.out.println("query12 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query13")
	public String query13() {
		List<User1VO> users = repo.findUser1VOByNameEndsWith("추");
		System.out.println("query13 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query14")
	public String query14() {
		List<User1VO> users = repo.findUser1VOByOrderByName();
		System.out.println("query14 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query15")
	public String query15() {
		List<User1VO> users = repo.findUser1VOByOrderByAgeAsc();
		System.out.println("query15 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query16")
	public String query16() {
		List<User1VO> users = repo.findUser1VOByOrderByAgeDesc();
		System.out.println("query16 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query17")
	public String query17() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThanOrderByAgeDesc(30);
		System.out.println("query17 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query18")
	public String query18() {
		int count = repo.countUser1VOByUid("a101");
		System.out.println("query18 결과 : " + count);
		return "redirect:/";
	}
	
	@GetMapping("/query19")
	public String query19() {
		int count = repo.countUser1VOByName("김춘추");
		System.out.println("query19 결과 : " + count);
		return "redirect:/";
	}
	
	@GetMapping("/query20")
	public String query20() {
		List<User1VO> users = repo.selectUserUnderAge30();
		System.out.println("query20 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query21")
	public String query21() {
		List<User1VO> users = repo.selectUserByName("장보고");
		System.out.println("query21 결과 : " + users);
		return "redirect:/";
	}
	
	@GetMapping("/query22")
	public String query22() {
		List<User1VO> users = repo.selectUserByNameWithParam("김유신");
		System.out.println("query22 결과 : " + users);
		return "redirect:/";
	}

}

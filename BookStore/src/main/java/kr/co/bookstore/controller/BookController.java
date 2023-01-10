package kr.co.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.bookstore.service.BookService;
import kr.co.bookstore.vo.BookVO;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService service;

	@GetMapping("/list")
	public String list(Model model) {
		List<BookVO> books = service.selectBooks();
		model.addAttribute("books", books);
		return "/book/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/book/register";
	}
	
	@PostMapping("/register")
	public String register(BookVO vo) {
		service.insertBook(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, int bookId) {
		BookVO book = service.selectBook(bookId);
		model.addAttribute("book", book);
		return "/book/modify";
	}
	
	@PostMapping("/modify")
	public String modify(BookVO vo) {
		service.updateBook(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("/delete")
	public String delete(int bookId) {
		service.deleteBook(bookId);
		return "redirect:/book/list";
	}
	
}

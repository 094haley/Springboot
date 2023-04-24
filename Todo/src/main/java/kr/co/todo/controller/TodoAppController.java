package kr.co.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.todo.service.TodoAppService;
import kr.co.todo.vo.TodoVO;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class TodoAppController {
	
	@Autowired
	private TodoAppService service;
	
	@PostMapping("todo")
	public void insertTodo(@RequestBody TodoVO vo) {
		service.insertTodo(vo);
	}
	
	@GetMapping("todos")
	public List<TodoVO> todos(){
		return service.selectTodos();
	}
	
	@DeleteMapping("todo")
	public void deleteTodo(@RequestBody int no) {
		service.deleteTodo(no);
	}
	
	@DeleteMapping("clear")
	public void clearTodos() {
		service.deleteAll();
	}
	
}

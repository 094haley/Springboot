package kr.co.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.todo.service.TodoService;
import kr.co.todo.vo.TodoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<TodoVO> todos = service.selectTodos();
		
		model.addAttribute("todos", todos);
		
		return "/index";
	}
	
	@ResponseBody
	@PostMapping("/insert")
	public Map<String , Object> insert(TodoVO vo) {
		
		int no = service.insertTodo(vo);
		TodoVO newtodo = service.selectTodo(no);
		
		Map<String , Object> resultMap = new HashMap<>();
		resultMap.put("result", no);
		resultMap.put("newtodo", newtodo);

		return resultMap;
	}

	@ResponseBody
	@PostMapping("/update")
	public Map<String , Integer> update(TodoVO vo) {
		
		int result = 0;
		
		result = service.updateTodo(vo);
		
		Map<String , Integer> resultMap = new HashMap<>();
		resultMap.put("result", result);

		return resultMap;
	}

	@ResponseBody
	@PostMapping("/delete")
	public Map<String , Integer> delete(int no) {
		

		
		int result = service.deleteTodo(no);
		
		
		Map<String , Integer> resultMap = new HashMap<>();
		resultMap.put("result", result);

		return resultMap;
	}
}


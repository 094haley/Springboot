package kr.co.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.todo.dao.TodoAppDAO;
import kr.co.todo.vo.TodoVO;

@Service
public class TodoAppService {
	
	@Autowired
	private TodoAppDAO dao;
	
	public void insertTodo(TodoVO vo) {
		dao.insertTodo(vo);
	}
	
	public List<TodoVO> selectTodos(){
		return dao.selectTodos();
	}
	
	public TodoVO selectTodo(int no) {
		return dao.selectTodo(no);
	}
	
	public void deleteTodo(int no) {
		dao.deleteTodo(no);
	}
	
	public void deleteAll() {
		dao.deleteAll();
	}

}

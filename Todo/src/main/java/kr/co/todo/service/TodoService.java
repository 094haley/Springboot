package kr.co.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.todo.dao.TodoDAO;
import kr.co.todo.vo.TodoVO;

@Service
public class TodoService {

	@Autowired
	private TodoDAO dao;
	
	public int insertTodo(TodoVO vo) {
		dao.insertTodo(vo);		
		return vo.getNo(); 
	}
	public List<TodoVO> selectTodos(){
		return dao.selectTodos();
	}
	public TodoVO selectTodo(int no){
		return dao.selectTodo(no);
	}
	public int updateTodo(TodoVO vo) {
		return dao.updateTodo(vo);
	}
	public int deleteTodo(int no) {
		return dao.deleteTodo(no);
	}
}

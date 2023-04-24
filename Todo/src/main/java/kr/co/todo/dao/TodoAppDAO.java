package kr.co.todo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.todo.vo.TodoVO;

@Mapper
@Repository
public interface TodoAppDAO {
	
	public void insertTodo(TodoVO vo);

	public TodoVO selectTodo(int no);
	
	public List<TodoVO> selectTodos();
	
	public void deleteTodo(int no);
	
	public void deleteAll();

}

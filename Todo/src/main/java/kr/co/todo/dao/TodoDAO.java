package kr.co.todo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

import kr.co.todo.vo.TodoVO;

@Mapper
@Repository
public interface TodoDAO {

	public int insertTodo(TodoVO vo);
	public List<TodoVO> selectTodos();
	public TodoVO selectTodo(int no);
	public int updateTodo(TodoVO vo);
	public int deleteTodo(int no);
}

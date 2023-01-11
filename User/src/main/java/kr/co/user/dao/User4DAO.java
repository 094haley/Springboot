package kr.co.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.User4VO;

@Mapper
@Repository
public interface User4DAO {
	
	public void insertUser4(User4VO vo);
	public User4VO selectUser4(int seq);
	public List<User4VO> selectUser4s();
	public void updateUser4(User4VO vo);
	public void deleteUser4(int seq);
}

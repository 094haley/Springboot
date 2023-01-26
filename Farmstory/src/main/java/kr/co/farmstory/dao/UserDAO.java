package kr.co.farmstory.dao;

import java.util.List;

import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserDAO {
	
	public int insertUser(UserVO vo);
	public UserVO selectUser(String uid);
	public List<UserVO> selectUsers();
	public int updateUsers(UserVO vo);
	public int deleteUsers(String uid);
	
	public TermsVO selectTerms();
	
}

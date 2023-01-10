package kr.co.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.User3VO;

@Repository
public class User3DAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser3(User3VO vo) {
		mybatis.insert("user3.insertUser3", vo);
	}
	
	public User3VO selectUser3(String uid) {
		return 	mybatis.selectOne("user3.selectUser3", uid);
	}
	
	public List<User3VO> selectUser3s() {
		return mybatis.selectList("user3.selectUser3s");
	}
	
	public void updateUser3(User3VO vo) {
		mybatis.update("user3.updateUser3", vo);
	}
	
	public void deleteUser3(String uid) {
		mybatis.delete("user3.deleteUser3", uid);
	}
	
}

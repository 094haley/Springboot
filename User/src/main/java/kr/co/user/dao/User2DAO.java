package kr.co.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.User2VO;

@Repository
public class User2DAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser2(User2VO vo) {
		mybatis.insert("user2.insertUser2", vo);
	}
	
	public User2VO selectUser2(String uid) {
		return mybatis.selectOne("user2.selectUser2", uid);
	}
	
	public List<User2VO> selectUser2s() {
		return mybatis.selectList("user2.selectUser2s");
	}
	
	public void updateUser2(User2VO vo) {
		mybatis.update("user2.updateUser2", vo);
	}
	
	public void deleteUser2(String uid) {
		mybatis.delete("user2.deleteUser2", uid);
	}
	
}

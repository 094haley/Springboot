package kr.co.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.User3DAO;
import kr.co.user.vo.User3VO;

@Service
public class User3Service {
	
	@Autowired
	private User3DAO dao;
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser3(User3VO vo) {
		dao.insertUser3(vo);
	}
	
	public User3VO selectUser3(String uid) {
		return dao.selectUser3(uid);
	}
	
	public List<User3VO> selectUser3s() {
		return dao.selectUser3s();
	}
	
	public void updateUser3(User3VO vo) {
		dao.updateUser3(vo);
	}
	
	public void deleteUser3(String uid) {
		dao.deleteUser3(uid);
	}

}

package kr.co.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.User2DAO;
import kr.co.user.vo.User2VO;

@Service
public class User2Service {

	@Autowired
	private User2DAO dao;
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser2(User2VO vo) {
		dao.insertUser2(vo);
	}
	
	public User2VO selectUser2(String uid) {
		return dao.selectUser2(uid);
	}
	
	public List<User2VO> selectUser2s() {
		return dao.selectUser2s();
	}
	
	public void updateUser2(User2VO vo) {
		dao.updateUser2(vo);
	}
	
	public void deleteUser2(String uid) {
		dao.deleteUser2(uid);
	}
}

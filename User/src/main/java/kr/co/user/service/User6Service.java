package kr.co.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.User6DAO;
import kr.co.user.vo.User6VO;


@Service
public class User6Service {
	
	@Autowired
	private User6DAO dao;
	
	public void insertUser6(User6VO vo) {
		dao.insertUser6(vo);
	}
	
	public User6VO selectUser6(String uid) {
		return dao.selectUser6(uid);
	}
	
	public List<User6VO> selectUser6s(){
		return dao.selectUser6s();
	}
	
	public void updateUser6(User6VO vo) {
		dao.updateUser6(vo);
	}
	
	public void deleteUser6(String uid) {
		dao.deleteUser6(uid);
	}
	
}

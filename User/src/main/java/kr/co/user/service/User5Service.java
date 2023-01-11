package kr.co.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.User5DAO;
import kr.co.user.vo.User5VO;

@Service
public class User5Service {
	
	@Autowired
	private User5DAO dao;
	
	public void insertUser5(User5VO vo) {
		dao.insertUser5(vo);
	}
	
	public User5VO selectUser5(String uid) {
		return dao.selectUser5(uid);
	}
	
	public List<User5VO> selectUser5s(){
		return dao.selectUser5s();
	}
	
	public void updateUser5(User5VO vo) {
		dao.updateUser5(vo);
	}
	
	public void deleteUser5(String uid) {
		dao.deleteUser5(uid);
	}
	
}

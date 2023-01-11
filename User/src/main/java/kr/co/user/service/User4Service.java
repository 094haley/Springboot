package kr.co.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.User4DAO;
import kr.co.user.vo.User4VO;

@Service
public class User4Service {
	
	@Autowired
	private User4DAO dao;
	
	public void insertUser4(User4VO vo) {
		dao.insertUser4(vo);
	}
	
	public User4VO selectUser4(int seq) {
		return dao.selectUser4(seq);
	}
	
	public List<User4VO> selectUser4s(){
		return dao.selectUser4s();
	}
	
	public void updateUser4(User4VO vo) {
		dao.updateUser4(vo);
	}
	
	public void deleteUser4(int seq) {
		dao.deleteUser4(seq);
	}
}

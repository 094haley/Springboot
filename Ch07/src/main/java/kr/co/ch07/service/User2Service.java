package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User2Repo;
import kr.co.ch07.vo.User2VO;

@Service
public class User2Service {
	
	@Autowired
	private User2Repo repo;
	
	public void insertUser2(User2VO vo) {
		repo.save(vo);
	}
	
	public User2VO selectUser2(String uid) {
		User2VO user = repo.findById(uid).get();
		return user;
	}
	
	public List<User2VO> selectUser2s() {
		
		List<User2VO> users = repo.findAll();
		return users;
		
	}
	
	public void updateUser2(User2VO vo) {
		repo.save(vo);
	}
	
	public void deleteUser2(String uid) {
		repo.deleteById(uid);
	}

}

package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User4Repo;
import kr.co.ch07.vo.User4VO;

@Service
public class User4Service {
	
	@Autowired
	private User4Repo repo;
	
	public void insertUser4(User4VO vo) {
		repo.save(vo);
	}
	
	public User4VO selectUser4(int seq) {
		User4VO user = repo.findById(seq).get();
		return user;
	}
	
	public List<User4VO> selectUser4s() {
		List<User4VO> users = repo.findAll();
		return users;
	}
	
	public void updateUser4(User4VO vo) {
		repo.save(vo);
	}
	
	public void deleteUser4(int seq) {
		repo.deleteById(seq);
	}
	
}

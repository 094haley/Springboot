package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User3Repo;
import kr.co.ch07.vo.User3VO;

@Service
public class User3Service {
	
	@Autowired
	private User3Repo repo;
	
	public void insertUser3(User3VO vo) {
		repo.save(vo);
	}
	
	public User3VO selectUser3(String uid) {
		User3VO user = repo.findById(uid).get();
		return user;
	}
	
	public List<User3VO> selectUser3s() {
		List<User3VO> users = repo.findAll();
		return users;
	}
	
	public void updateUser3(User3VO vo) {
		repo.save(vo);
	}
	
	public void deleteUser3(String uid) {
		repo.deleteById(uid);
	}
	
}

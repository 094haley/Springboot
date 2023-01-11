package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User5Repo;
import kr.co.ch07.vo.User5VO;

@Service
public class User5Service {
	
	@Autowired
	private User5Repo repo;
	
	public void insertUser5(User5VO vo) {
		repo.save(vo);
	}
	
	public User5VO selectUser5(String uid) {
		User5VO user = repo.findById(uid).get();
		return user;
	}
	
	public List<User5VO> selectUser5s() {
		List<User5VO> users = repo.findAll();
		return users;
	}
	
	public void updateUser5(User5VO vo) {
		repo.save(vo);
	}
	
	public void deleteUser5(String uid) {
		repo.deleteById(uid);
	}

}

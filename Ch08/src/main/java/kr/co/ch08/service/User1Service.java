package kr.co.ch08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch08.repository.User1Repo;
import kr.co.ch08.vo.User1VO;

@Service
public class User1Service {
	
	@Autowired
	private User1Repo repo;
	
	public User1VO selectUser1(String uid, String pass) {
		return repo.findUser1VOByUidAndPass(uid, pass);
	}
	
}

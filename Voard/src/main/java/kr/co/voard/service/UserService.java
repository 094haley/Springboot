package kr.co.voard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.voard.dao.UserDAO;
import kr.co.voard.entity.UserEntity;
import kr.co.voard.repository.UserRepo;
import kr.co.voard.vo.TermsVO;
import kr.co.voard.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertUser(UserVO vo) {
		
		String passEncoded = passwordEncoder.encode(vo.getPass1());
		vo.setPass(passEncoded);
		
		dao.insertUser(vo);
	}
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	public UserVO selectUser(String uid) {
		return dao.selectUser(uid);
	}
	public List<UserVO> selectUsers(){
		return dao.selectUsers();
	}
	public void updateUser(UserVO vo) {
		dao.updateUser(vo);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
}

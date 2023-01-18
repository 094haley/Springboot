package kr.co.sboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.repository.UserRepo;
import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@Service
public class UserService {
		
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	public int insertUser(UserVO vo) {
		vo.setPass(encoder.encode(vo.getPass1()));
		return dao.insertUser(vo);
	}
	public UserVO selectUser(String uid) {
		return dao.selectUser(uid);
	}
	public List<UserVO> selectUsers() {
		return dao.selectUsers();
	}
	public int updateUsers(UserVO vo) {
		return dao.updateUsers(vo);
	}
	public int deleteUsers(String uid) {
		return dao.deleteUsers(uid);
	}
	
	public int countByUid(String uid) {
		return repo.countByUid(uid);
	}
	
}

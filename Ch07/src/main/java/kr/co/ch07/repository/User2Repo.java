package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User2VO;

@Repository
public interface User2Repo extends JpaRepository<User2VO, String>{

}

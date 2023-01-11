package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User3VO;

@Repository
public interface User3Repo extends JpaRepository<User3VO, String> {

}

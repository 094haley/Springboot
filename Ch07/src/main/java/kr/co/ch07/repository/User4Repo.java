package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User4VO;

@Repository
public interface User4Repo extends JpaRepository<User4VO, Integer> {

}

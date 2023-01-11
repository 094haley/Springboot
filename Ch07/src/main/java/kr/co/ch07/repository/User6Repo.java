package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User6VO;

@Repository
public interface User6Repo extends JpaRepository<User6VO, String> {

}

package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User5VO;

@Repository
public interface User5Repo extends JpaRepository<User5VO, String> {

}

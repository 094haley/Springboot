package kr.co.bookstore.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookstore.vo.CustomerVO;

@Repository
public class CustomerDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCustomer(CustomerVO vo) {
		mybatis.insert("customer.insertCustomer", vo);
	}
	
	public CustomerVO selectCustomer(int custId) {
		return mybatis.selectOne("customer.selectCustomer", custId);
	}
	
	public List<CustomerVO> selectCustomers() {
		return mybatis.selectList("customer.selectCustomers");
	}
	
	public void updateCustomer(CustomerVO vo) {
		mybatis.update("customer.updateCustomer", vo);
	}
	
	public void deleteCustomer(int custId) {
		mybatis.delete("customer.deleteCustomer", custId);
	}
	
}

package cn.dlian.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import cn.dlian.entities.Administrator;
import cn.dlian.entities.Customer;

@Transactional
public class CustomerDaoImpl implements ICustomerDao {
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/**
	 * 客户登陆
	 */
	@Override
	public boolean login(int id, String password) {
		String statement = "cn.dlian.entities.CustomerMapper.login";
		Customer cus = new Customer();
		cus.setId(id);
		cus.setPassword(password);
		cus = sqlSession.selectOne(statement, cus);
		return cus!=null;
	}

	/**
	 * 客户修改密码
	 */
	@Override
	public boolean updatePassword(int id, String newPass) {
		String statement = "cn.dlian.entities.CustomerMapper.updatePassword";
		Customer cus = new Customer();
		cus.setId(id);
		cus.setPassword(newPass);
		int x= sqlSession.update(statement, cus);
		return x>0;
	}

	/**
	 * 客户注册
	 */
	@Override
	public boolean regist(Customer cus) {
		String statement = "cn.dlian.entities.CustomerMapper.regist";
		int x = sqlSession.insert(statement,cus);
		return x>0;
	}

	/**
	 * 客户信息查询
	 */
	@Override
	public Customer queryInfo(int id) {
		String statement = "cn.dlian.entities.CustomerMapper.queryInfo";
		Customer cus = sqlSession.selectOne(statement,id);
		return cus;
	}

	/**
	 * 客户修改信息
	 */
	@Override
	public boolean updateInfo(Customer cus) {
		String statement = "cn.dlian.entities.CustomerMapper.updateInfo";
		int x = sqlSession.update(statement, cus);
		return x>0;
	}

}
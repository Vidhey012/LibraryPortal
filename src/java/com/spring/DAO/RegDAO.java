package com.spring.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.Model.Registration;


@Repository
public class RegDAO extends BaseDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public Boolean add(Registration regModel) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p", regModel);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Integer a = sqlSession.insert("Reg.Insert", params);
		sqlSession.close();
		if(a != 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public List<Registration> getAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Registration> userdetails=sqlSession.selectList("Reg.getAll");
		sqlSession.close();
		return userdetails;
	}
	public Registration getById(String name)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Registration registration=sqlSession.selectOne("Reg.getById",name);
		sqlSession.close();
		return registration;
		
	}
	public void updateData(Registration regModel)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p", regModel);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("Reg.updateData",params);
		sqlSession.close();
	}
	public void changeData(Registration regModel)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p", regModel);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("Reg.changeData",params);
		sqlSession.close();
	}
	public void remove(String name)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("Reg.deleteData",name);
		sqlSession.close();
	}
	public Integer getCounts(Registration regModel)
	{
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("p",regModel);

	Integer userStatus = sqlSessionTemplate.selectOne("Reg.getCount", map);
	return userStatus;
	}

	public void save(Registration regModel)   
	{
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("p",regModel);

	sqlSessionTemplate.insert("Reg.add", map);

	}

	public Registration getDetails(Registration regModel)
	{
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("p",regModel);
	Registration details = sqlSessionTemplate.selectOne("Reg.get", map);
	return details;
	}

}

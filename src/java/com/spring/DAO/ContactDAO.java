
package com.spring.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.Model.Contact;

@Repository
public class ContactDAO extends BaseDAO {
@Autowired
private SqlSessionFactory sqlSessionFactory;

public void add(Contact ContactModel) {
Map<String, Object> params = new HashMap<String, Object>();
params.put("p", ContactModel);
SqlSession sqlSession = sqlSessionFactory.openSession();
sqlSession.insert("Contact.Insert", params);
sqlSession.close();
}                      
public List<Contact> getAll() {
SqlSession sqlSession = sqlSessionFactory.openSession();
List<Contact> condetails=sqlSession.selectList("Contact.getAll");
sqlSession.close();
return condetails;
}


}
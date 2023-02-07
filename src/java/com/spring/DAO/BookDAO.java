
package com.spring.DAO;

import java.util.HashMap;
import java.util.List;
/*import java.util.List;*/
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.Model.Book;
import com.spring.Model.Contact;
import com.spring.Model.Registration;

@Repository
public class BookDAO extends BaseDAO {
@Autowired
private SqlSessionFactory sqlSessionFactory;

public Boolean add(Book BookModel) {
Map<String, Object> params = new HashMap<String, Object>();
params.put("p", BookModel);
SqlSession sqlSession = sqlSessionFactory.openSession();
Integer a = sqlSession.insert("Book.Insert", params);
sqlSession.close();
if(a != 0) {
return true;
}
else {
return false;
}
}

public List<Book> getAll() {
SqlSession sqlSession = sqlSessionFactory.openSession();
List<Book> bookdetails=sqlSession.selectList("Book.getAll");
sqlSession.close();
return bookdetails;
}


}
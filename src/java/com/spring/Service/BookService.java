
package com.spring.Service;

import java.util.List;

/*import java.util.List;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andromeda.commons.model.Response;
import com.spring.DAO.BookDAO;
import com.spring.Model.Book;
import com.spring.Model.Registration;

@Service
public class BookService {

Response response = new Response();

@Autowired
private BookDAO BookDAO;
public Response add(Book BookModel) {
response.setSuccessful(false);
BookDAO.add(BookModel);
response.setSuccessful(true);
response.setResponseObject(BookModel);
return response;
}

public Response getAll() {
response.setSuccessful(false);
List<Book> bookdetails = BookDAO.getAll();
response.setSuccessful(true);
response.setResponseObject(bookdetails);
return response;
}
}  
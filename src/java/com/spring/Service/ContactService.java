
package com.spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andromeda.commons.model.Response;
import com.spring.DAO.ContactDAO;
import com.spring.Model.Contact;

@Service
public class ContactService {

Response response = new Response();

@Autowired
private ContactDAO contactDAO;
public Response add(Contact ContactModel) {
response.setSuccessful(false);
contactDAO.add(ContactModel);
response.setSuccessful(true);
response.setResponseObject(ContactModel);
return response;
}
public Response getAll() {
response.setSuccessful(false);
List<Contact> condetails = contactDAO.getAll();
response.setSuccessful(true);
response.setResponseObject(condetails);
return response;
}


}


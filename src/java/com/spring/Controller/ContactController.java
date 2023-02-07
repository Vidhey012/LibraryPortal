package com.spring.Controller;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andromeda.commons.model.Response;
import com.spring.Model.Contact;
import com.spring.Service.ContactService;

@RestController
@RequestMapping("/Contact")
public class ContactController {    


@Autowired
private ContactService ContactService;

@ResponseBody
@RequestMapping(value = "add", method = { RequestMethod.POST })
public Response add(@RequestBody Contact ContactModel)
{
return ContactService.add(ContactModel);
}
@ResponseBody
@RequestMapping(value = "getAll", method = { RequestMethod.POST, RequestMethod.GET })
public Response getAll()
{
return ContactService.getAll();
}

}


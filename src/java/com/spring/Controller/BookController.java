
package com.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.andromeda.commons.model.Response;
import com.andromeda.commons.util.HttpUtils;
import com.spring.Model.Book;
import com.spring.Service.BookService;



@RestController
@RequestMapping("/Book1")
public class BookController {

@Autowired
private BookService BookService;

                  
@ResponseBody
@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
public Response add(@RequestBody Book BookModel,
HttpServletRequest httpServletRequest) throws JSONException
{
String clientProxyIp = HttpUtils.getClientProxyAddress(httpServletRequest);
String clientIp = HttpUtils.getClientAddress(httpServletRequest);
String ipAddress = "CLIENT:" + clientIp + ", CLIENT_PROXY:" + clientProxyIp;
  /* regModel.setipAddress(ipAddress);*/
return BookService.add(BookModel);
}



@ResponseBody
@RequestMapping(value = "getAll", method = { RequestMethod.POST, RequestMethod.GET })
public Response getAll()
{
return BookService.getAll();
}

}
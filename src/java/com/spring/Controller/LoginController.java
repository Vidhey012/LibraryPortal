package com.spring.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andromeda.commons.model.Response;
import com.andromeda.commons.util.HttpUtils;

import commons.util.Base64;
/*import in.apssdc.model.FileModel;*/
import com.spring.Model.Login;
import com.spring.Service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController
{
@Autowired
private LoginService loginService;

@ResponseBody
@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
public Response login(@RequestBody Login login, HttpServletRequest httpServletRequest)
throws UnsupportedEncodingException, NoSuchAlgorithmException
{
String clientProxyIp = HttpUtils.getClientProxyAddress(httpServletRequest);
String clientIp = HttpUtils.getClientAddress(httpServletRequest);
String ipAddress = "CLIENT:" + clientIp + ", CLIENT_PROXY:" + clientProxyIp;
login.setIpAddress(ipAddress);
return loginService.login(login);
}

@ResponseBody
@RequestMapping(value = "loggedin", method = { RequestMethod.POST })
public Boolean isUserLoggedIn(@RequestBody Login login)
{
Boolean status = loginService.isUserLoggedIn(login);
return status;
}
              
@ResponseBody
@RequestMapping(value = "/logout", method = { RequestMethod.POST })
public void logout(@RequestBody Login login)
{
String email = login.getEmail();
if (email != null)
{
loginService.logout(email);
}
}
/*
@ResponseBody
@RequestMapping(value = "validateAadhaar", method = { RequestMethod.POST })
public Response validateAadhaar(@RequestBody String aadhaar)
{
System.out.println("---------------");
System.out.println(aadhaar);
return loginService.validateAadhaar(aadhaar);
}
*/

/*public String handleFileUpload(@RequestBody FileModel fileModel)
{
FileOutputStream fos = null;
String file = null;
try
{
String folderName = fileModel.getFolderName();
String imageValue = fileModel.getBase64String();
byte[] imageByteArray = decodeImage(imageValue);

String baseDir =
System.getProperty("catalina.base") + "/webapps/siemensDocs/" + folderName;
File dir = new File(baseDir);
if (!Files.isDirectory(Paths.get(baseDir)))
{
dir.mkdirs();
}
fos = new FileOutputStream(baseDir + "/" + fileModel.getName());
file =
System.getProperty("catalina.base") + "/webapps/siemensDocs/" + folderName
+ "/" + fileModel.getName();

fos.write(imageByteArray);
fos.close();

}
catch (Exception e)
{
System.out.println("Exception: " + e.getMessage());
}
return file;
}

public static byte[] decodeImage(String imageValue)
{
return Base64.decode(imageValue);
}*/

}
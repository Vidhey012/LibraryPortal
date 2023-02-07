package com.spring.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andromeda.commons.model.Response;
import com.spring.DAO.RegDAO;

import com.spring.Model.Registration;
import com.spring.Model.Email;






@Service
public class RegService {
	
	@Autowired
	EmailService emailService;

	

	@Autowired
	private RegDAO regDAO;
	Response response = new Response();

	public Response add(Registration regModel) {
		response.setSuccessful(false);		
	/*	regModel.setProgramCategory("SIP");
		regModel.setProgramName("mechatronics");
		regModel.setCourseId("ARC 1.0");*/
		
		
		regDAO.add(regModel);
		
		Email email = new Email();
		email.setFrom("Library <workshops@apssdc.in>");
		email.setTo(regModel.getEmail().trim());
		email.setSubject("Libarary - online management system ");
		String msg = "Dear Sir/Madam," + "<br><br>"
				+ " Welcome to Library online management system in Gudlavalleru.";
				
		email.setText(msg);
		emailService.sendHtmlMsg(email);
		
		
		response.setSuccessful(true);
		response.setResponseObject(regModel);
		return response;
	}
	public Response getAll() {
		response.setSuccessful(false);
		List<Registration> userdetails = regDAO.getAll();
		response.setSuccessful(true);
		response.setResponseObject(userdetails);
		return response;
	}
	public Response getById(String name)
	{
		response.setSuccessful(false);
		Registration singleuserdetails = regDAO.getById(name);
		response.setSuccessful(true);
		response.setResponseObject(singleuserdetails);
		return response;
	}
	public Response updateData(Registration regModel)
	{
		response.setSuccessful(false);
		regDAO.updateData(regModel);
		response.setSuccessful(true);
		response.setResponseObject(regModel);
		return response;
	}
	public Response changeData(Registration regModel)
	{
		response.setSuccessful(false);
		regDAO.changeData(regModel);
		response.setSuccessful(true);
		response.setResponseObject(regModel);
		return response;
	}
	public Response remove(String name)
	{
		response.setSuccessful(false);
		regDAO.remove(name);
		response.setSuccessful(true);
		response.setResponseObject(name);
		return response;
	}
	public Response forgot(Registration regModel)
	{
	response.setSuccessful(false);

	/*login.setPassword(CryptoUtils.getMD5Hash(login.getPassword()));*/
	Integer userStatus = regDAO.getCounts(regModel);

	if (userStatus==0)
	{
	response.setSuccessful(false);
	}
	else if(userStatus > 0)
	{
	Registration details = regDAO.getDetails(regModel);
	regDAO.save(details);  
	Email email = new Email();
	         email.setFrom("Librarydata <joinus@apssdc.in>");
	     email.setTo(regModel.getEmail().trim());
	    email.setSubject("Library- Email code verification ");   
	     String msg = "Dear Sir/Madam," + "<br><br>"
	         + " Your username and password are as follows"
	    +  "Email id:"+details.getEmail()
	     +" Password:" +details.getPassword();

	      email.setText(msg);
	                  emailService.sendHtmlMsg(email);

	             email.setText(msg);
	            emailService.sendHtmlMsg(email);

	response.setSuccessful(true);
	response.setResponseObject(details);
	}
	return response;
	}
}

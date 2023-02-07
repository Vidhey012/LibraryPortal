package com.spring.Service;

import com.spring.Model.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.andromeda.commons.model.Response;
import com.spring.DAO.LoginDAO;
import com.andromeda.commons.util.AadhaarUtils;
import com.andromeda.commons.util.CryptoUtils;
import com.andromeda.commons.util.IDGenerator;

@Service
public class LoginService
{
	@Autowired
	private LoginDAO loginDAO;

	

	Response response = new Response();

	public Response login(Login login)
	{
		response.setSuccessful(false);
		
		/*login.setPassword(CryptoUtils.getMD5Hash(login.getPassword()));*/
		Integer userStatus = loginDAO.checkUser(login);

		if (userStatus == 0)
		{
			response.setSuccessful(false);
		}
		else if (userStatus > 0)
		{
			Login details = loginDAO.getUserDetails(login);
			
			details.setIpAddress(login.getIpAddress());
			details.setIpAddress("");
      
			loginDAO.saveToLogins(details);

			response.setSuccessful(true);
			response.setResponseObject(details);
		}
		return response;
	}
	
	public Boolean isUserLoggedIn(Login login)
	{
		Boolean status = false;
		if ((!StringUtils.isEmpty(login.getEmail()))
				&& (!StringUtils.isEmpty(login.getPassword())))
		{
			Integer loginStatus = loginDAO.isUserLoggedIn(login);
			/*System.out.println("-------------------------");
			System.out.println(loginStatus);
			System.out.println("-------------------------");*/
			if (loginStatus == 0)
			{
				status = false;
			}
			else if (loginStatus > 0)
			{
				status = true;
			}
		}

		return status;
	}
	
	
	
	
	
	
	
	
	
	public void logout(String email)
	{
		loginDAO.deleteLoginLog(email);
	}

}
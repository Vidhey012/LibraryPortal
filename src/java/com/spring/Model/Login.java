package com.spring.Model;

import com.andromeda.commons.model.BaseModel;

public class Login extends BaseModel
{
	
	private String email;
	private String password;
	private String ipAddress;
	private String type;
	private String logtime;

	public String getType()
	{              
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}   
	public String getLogtime()
	{
		return logtime;
	}

	public void setLogtime(String logtime)
	{
		this.logtime = logtime;
	}
}
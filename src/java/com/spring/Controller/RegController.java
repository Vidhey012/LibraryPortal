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
import com.spring.Model.Registration;
import com.spring.Service.RegService;



@RestController
@RequestMapping("/register1")
public class RegController {

	@Autowired
	private RegService regService;
	
	
	@ResponseBody
	@RequestMapping(value = { "/saveDetails" }, method = { RequestMethod.POST })
	public Response saveStudentRegistrationDetails(@RequestBody Registration regModel,
			HttpServletRequest httpServletRequest) throws JSONException
	{
		String clientProxyIp = HttpUtils.getClientProxyAddress(httpServletRequest);
		String clientIp = HttpUtils.getClientAddress(httpServletRequest);
		String ipAddress = "CLIENT:" + clientIp + ", CLIENT_PROXY:" + clientProxyIp;
	/*	regModel.setIpAddress(ipAddress);*/
		return regService.add(regModel);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "getAll", method = { RequestMethod.POST, RequestMethod.GET })
	public Response getAll()
	{
		return regService.getAll();
	}
	@ResponseBody
	@RequestMapping(value = "getById", method = { RequestMethod.POST })
	public Response getById(@RequestBody String name)
	{
		return regService.getById(name);
	}
	@ResponseBody
	@RequestMapping(value = "updateData", method = { RequestMethod.POST })
	public Response updateData(@RequestBody Registration regModel)
	{
		return regService.updateData(regModel);
	}
	@ResponseBody
	@RequestMapping(value = "changeData", method = { RequestMethod.POST })
	public Response changeData(@RequestBody Registration regModel)
	{
		return regService.changeData(regModel);
	}
	@ResponseBody
	@RequestMapping(value = "removeData", method = { RequestMethod.POST })
	public Response remove(@RequestBody String name)
	{
		return regService.remove(name);
	}
	@ResponseBody
	@RequestMapping(value = "forgot", method = { RequestMethod.POST })
	public Response forgot(@RequestBody String email,Registration regModel)
	throws JSONException
	{
		regModel.setEmail(email);
		Response response = regService.forgot(regModel);
		return response;
	}
}

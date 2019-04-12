package com.ssi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.ProviderDAO;
import com.ssi.dao.ServiceDAO;
import com.ssi.dao.UserDAO;
import com.ssi.entities.Provider;
import com.ssi.entities.Service;
import com.ssi.entities.User;

@Controller
public class UserController {
	
	@Autowired
	ServiceDAO serviceDAO;
	@Autowired
	ProviderDAO providerDAO;
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("searchbyjob")
	public ModelAndView searchByJob(@RequestParam("sname") String scode){
		List<Provider> providerList=providerDAO.getAllProvidersByJob(scode);
		ModelAndView mv=new ModelAndView("providerlist");
		mv.addObject("providers", providerList);
		return mv;
	}
	
	@RequestMapping("searchproviderbyjob")
	public ModelAndView showSearchByJobForm(){
		ModelAndView mv=new ModelAndView("searchproviderbyjobform");
		mv.addObject("service", new Service());
		Map<String,String> serviceMap=serviceDAO.getAllServiceCodeAndNameMap();
		mv.addObject("services",serviceMap);
		return mv;
	}
	
	
	@RequestMapping("allproviders")
	public ModelAndView showAllProviders(){
		List<Provider> providerList=providerDAO.getAllProviders();
		ModelAndView mv=new ModelAndView("providerlist");
		mv.addObject("providers", providerList);
		return mv;
	}
	@RequestMapping("userdashboard")
	public String showUserDashboard(){
		return "userdashboard";
	}
	@RequestMapping("verifyuser")
	public ModelAndView verifyUserData(@ModelAttribute("user") User user){
		boolean success=userDAO.verifyUser(user);
		if(success){
		ModelAndView mv=new ModelAndView("userdashboard");
		return mv;
		}else{
			ModelAndView mv=new ModelAndView("userlogin");
			return mv;
		}
	}
	@RequestMapping("userlogin")
	public String showUserLoginForm(){
		return "userlogin";
	}
	@RequestMapping("newuser")
	public String showUserRegistrationForm(){
		return "userregistration";
	}
	
	@RequestMapping("saveuser")
	public ModelAndView saveUserData(@ModelAttribute("user") User user){
		userDAO.saveOrUpdateUser(user);
		ModelAndView mv=new ModelAndView("usersuccess");
		return mv;
	}
}

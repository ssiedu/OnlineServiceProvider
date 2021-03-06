package com.ssi.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.ProviderDAO;
import com.ssi.dao.ServiceDAO;
import com.ssi.entities.Provider;
import com.ssi.entities.User;

@Controller
public class ProviderController {
	
	@Autowired
	JavaMailSender jms;
	
	@Autowired
	ProviderDAO providerDAO;
	@Autowired
	ServiceDAO serviceDAO;
	
	@RequestMapping("providerlogin")
	public String showProviderLoginForm(){
		return "providerlogin";
	}
	
	@RequestMapping("verifyprovider")
	public ModelAndView verifyProviderData(@ModelAttribute("provider") Provider provider, HttpServletRequest request){	
		boolean success=providerDAO.verifyProvider(provider);
		System.out.println(success);
		if(success){
		HttpSession session=request.getSession();
		session.setAttribute("providerid", provider.getEmail());
		ModelAndView mv=new ModelAndView("providerdashboard");
		return mv;
		}else{
			ModelAndView mv=new ModelAndView("providerlogin");
			return mv;
		}
	}
	
	
	
	@RequestMapping("sendpricelist")
	public String sendMailForPrices(){
		
		//email addresses of all providers
		//price-list for all services
		String ids[]=providerDAO.getAllProviderEmails();
		String msg=serviceDAO.getPriceList();
		
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setTo(ids);
		smm.setSubject("Updated Price List");
		smm.setText(msg);
		jms.send(smm);
		return "mailconfirm";
	}
	
	@RequestMapping("newprovider")
	public ModelAndView showProviderRegistrationForm(){
		ModelAndView mv=new ModelAndView("providerregistration");
		Provider provider=new Provider();
		mv.addObject("provider", provider);
		Map<String,String> serviceMap=serviceDAO.getAllServiceCodeAndNameMap();
		mv.addObject("scodes",serviceMap);
		return mv;
	}
	
	/*@RequestMapping("newprovider")
	public String showProviderRegistrationForm(){
		return "providerregistration";
	}
	*/
	@RequestMapping("saveprovider")
	public ModelAndView saveProviderData(@ModelAttribute("provider") Provider provider){
		System.out.println(provider);
		providerDAO.saveProvider(provider);
		ModelAndView mv=new ModelAndView("providersuccess");
		return mv;
	}
}

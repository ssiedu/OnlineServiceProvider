package com.ssi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.ServiceDAO;
import com.ssi.entities.Service;

@Controller
public class ServiceController {

	@Autowired
	ServiceDAO serviceDAO;
	
	@RequestMapping("saveservicechanges")
	public ModelAndView saveServiceChanges(@ModelAttribute("service") Service service){
		serviceDAO.addSerive(service);
		ModelAndView mv=new ModelAndView("redirect:viewallservices");
		return mv;
	}
	
	@RequestMapping("updateservice")
	public ModelAndView showUpdateForm(@RequestParam("scode") String scode){
	
		Service service=serviceDAO.getServiceById(scode);
		ModelAndView mv=new ModelAndView("serviceupdateform");
		mv.addObject("service", service);
		return mv;
	}
	
	@RequestMapping("deleteservice")
	public ModelAndView removeService(@RequestParam("scode") String code){
		serviceDAO.removeService(code);
		ModelAndView mv=new ModelAndView("redirect:viewallservices");
		return mv;
	}
	
	@RequestMapping("viewallservices")
	public ModelAndView showServiceList(){
		List<Service> list=serviceDAO.getAllServices();
		ModelAndView mv=new ModelAndView("servicelist");
		mv.addObject("services", list);
		return mv;
	}
	@RequestMapping("saveservice")
	public ModelAndView saveServiceData(@ModelAttribute("service") Service service){
		serviceDAO.addSerive(service);
		ModelAndView mv=new ModelAndView("serviceentrysuccess");
		return mv;
	}
	@RequestMapping("newservice")
	public String showServiceEntryForm(){
		return "serviceentry";
	}
	@RequestMapping("adminhome")
	public String showAdminHome(){
		return "adminhome";
	}
}

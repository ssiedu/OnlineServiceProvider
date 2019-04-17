package com.ssi.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.MessageDAO;
import com.ssi.entities.Message;

@Controller
public class MessageController {
	@Autowired
	private MessageDAO messageDAO;
	
	
	@RequestMapping("providerinbox")
	public ModelAndView showMessagesByProvider(HttpServletRequest request){
		String pid=(String)request.getSession().getAttribute("providerid");
		List<Message> messages=messageDAO.getMessagesByProvider(pid);
		ModelAndView mv=new ModelAndView("messagelist");
		mv.addObject("messages", messages);
		return mv;
	}
	
	@RequestMapping("messageform")
	public String showMessageForm(){
		return "messageform";
	}
	
	@RequestMapping("savemessage")
	public ModelAndView saveMessage(@ModelAttribute("message") Message message){
		java.util.Date dt=new java.util.Date();
		long val=dt.getTime();
		message.setMessageId(val);
		messageDAO.saveMessage(message);
		ModelAndView mv=new ModelAndView("messagesuccess");
		return mv;
	}
}

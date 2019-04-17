package com.ssi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entities.Message;

@Component
public class MessageDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Message> getMessagesByProvider(String email){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Message.class);
		Criterion crt=Restrictions.eq("provider.email", email);
		cr.add(crt);
		List<Message> messages=cr.list();
		session.close();
		return messages;
	}
	public void saveMessage(Message message){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(message);
		tr.commit();
		session.close();
	}
	
	
	
}

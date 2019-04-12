package com.ssi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entities.Provider;

@Component
public class ProviderDAO {

	@Autowired
	SessionFactory sessionFactory;
	

	public String[] getAllProviderEmails(){
		
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Provider");
		List<Provider> providers=query.list();
		int size=providers.size();
		String emails[]=new String[size];
		for(int i=0; i<providers.size();i++){
			emails[i]=providers.get(i).getEmail();
		}
		return emails;
	}
	public List<Provider> getAllProvidersByJob(String scode){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Provider.class);
		Criterion crt=Restrictions.eq("Service.scode", scode);
		cr.add(crt);
		List<Provider> providerList=cr.list();
		session.close();
		return providerList;
	}

	
	public List<Provider> getAllProviders(){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Provider.class);
		List<Provider> providerList=cr.list();
		session.close();
		return providerList;
	}
	public void saveProvider(Provider provider){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(provider);
		tr.commit();
		session.close();
	}
}

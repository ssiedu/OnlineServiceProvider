package com.ssi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entities.Provider;
import com.ssi.entities.Service;

@Component
public class ServiceDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public String getPriceList(){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Service.class);
		List<Service> services=cr.list();
		String s="Our Service Charges : \n";
		for(Service service:services){
			s=s+service.getSname()+","+service.getCharge()+"\n";
		}
		return s;
	}
	public Map<String,String> getAllServiceCodeAndNameMap(){
		
		Map<String,String> serviceMap=new HashMap<String,String>();
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Service.class);
		List<Service> serviceList=cr.list();
		
		for(Service service:serviceList){
			String key=service.getScode();
			String value=service.getSname();
			serviceMap.put(key, value);
		}
		session.close();
		return serviceMap;
	}

	public List<String> getAllServiceCodes(){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Service.class);
		List<Service> serviceList=cr.list();
		List<String> list=new ArrayList<String>();
		for(Service service:serviceList){
			list.add(service.getScode());
		}
		session.close();
		return list;
	}
	
	public Service getServiceById(String scode){
		Session session=sessionFactory.openSession();
		Service service=session.get(Service.class, scode);
		session.close();
		return service;
	}
	
	public void removeService(String code){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Service service=new Service(); 
		service.setScode(code);
		session.delete(service);
		tr.commit();
		session.close();
	}
	public List<Service> getAllServices(){
		Session session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(Service.class);
		List<Service> serviceList=cr.list();
		session.close();
		return serviceList;
	}
	public void addSerive(Service service){
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.saveOrUpdate(service);
		tr.commit();
		session.close();
	}
}

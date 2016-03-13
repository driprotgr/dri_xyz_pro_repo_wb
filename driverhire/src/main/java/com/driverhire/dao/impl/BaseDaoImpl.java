package com.driverhire.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.driverhire.dao.BaseDao;

@Repository
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}
	
	public Session getSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	public void saveOrUpdateObject(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}
	public void createObject(Object obj) {
		
	}
	
	public void updateObject(Object obj) {
		getHibernateTemplate().update(obj);
	}
	
	public void deleteObject(Class c, Serializable id) {
		getSession().delete(getObject(c, id));
	}

	public Object getObject(Class c, Serializable id) {
		return getSession().get(c, id);
	}
	
	public Object getListObject(Class c) {
		return getSession().createCriteria(c).list();
	}
}

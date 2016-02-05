package com.driverhire.dao;

import java.io.Serializable;

import org.hibernate.Session;

public interface BaseDao {
	
	public Session getSession();
	
	public void saveOrUpdateObject(Object obj);

	public void deleteObject(Class c, Serializable id);

	public Object getObject(Class c, Serializable id);
	
	public Object getListObject(Class c);
}

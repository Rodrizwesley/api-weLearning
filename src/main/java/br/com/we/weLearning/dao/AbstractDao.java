package br.com.we.weLearning.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository("AbstractDao")
public abstract class AbstractDao<PK extends Serializable, T> { 
    
    private final Class<T> persistentClass; 
      
    @SuppressWarnings("unchecked") 
    public AbstractDao(){ 
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]; 
    } 

    @Autowired
    private SessionFactory sessionFactory; 
  
    protected Session getSession(){ 
        return sessionFactory.getCurrentSession(); 
    } 
  
    @SuppressWarnings("unchecked") 
    public T getByKey(PK key) { 
        return (T) getSession().get(persistentClass, key); 
    } 
  
    public void persist(T entity) { 
        getSession().persist(entity); 
    } 
  
    public void delete(T entity) throws Exception{ 
        getSession().delete(entity); 
    } 
      
    protected Criteria createEntityCriteria(){ 
        return getSession().createCriteria(persistentClass); 
    } 
}
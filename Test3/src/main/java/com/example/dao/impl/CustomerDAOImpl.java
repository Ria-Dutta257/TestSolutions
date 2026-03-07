package com.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import com.example.dao.CustomerDAO;
import com.example.entity.Customer;
import com.example.util.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO {

EntityManager em = HibernateUtil.getEntityManager();

public String saveCustomer(Customer customer){

em.getTransaction().begin();

em.persist(customer);

em.getTransaction().commit();

return "Customer Saved";

}

public String updateCustomer(Customer customer){

em.getTransaction().begin();

em.merge(customer);

em.getTransaction().commit();

return "Customer Updated";

}

public String deleteCustomerById(int id){

Customer c = em.find(Customer.class,id);

if(c!=null){

em.getTransaction().begin();

em.remove(c);

em.getTransaction().commit();

return "Customer Deleted";

}

return "Customer Not Found";

}

public Customer getCustomerById(int id){

return em.find(Customer.class,id);

}

public List<Customer> getAllCustomers(){

String jpql="SELECT c FROM Customer c";

TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);

return query.getResultList();

}

public Customer getCustomerByEmail(String email){

String jpql="SELECT c FROM Customer c WHERE c.email=:email";

TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);

query.setParameter("email",email);

return query.getSingleResult();

}

}
package com.example.dao.impl;


import com.example.dao.OrderDAO;
import com.example.entity.Order;
import com.example.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class OrderDAOImpl implements OrderDAO {

EntityManager em = HibernateUtil.getEntityManager();

public String saveOrder(Order order){

em.getTransaction().begin();

em.persist(order);

em.getTransaction().commit();

return "Order Saved";

}

public String updateOrder(Order order){

em.getTransaction().begin();

em.merge(order);

em.getTransaction().commit();

return "Order Updated";

}

public String deleteOrderById(int id){

Order order = em.find(Order.class,id);

if(order!=null){

em.getTransaction().begin();

em.remove(order);

em.getTransaction().commit();

return "Order Deleted";

}

return "Order Not Found";

}

public Order getOrderById(int id){

return em.find(Order.class,id);

}

}
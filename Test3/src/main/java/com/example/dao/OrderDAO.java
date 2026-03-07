package com.example.dao;

import com.example.entity.Order;

public interface OrderDAO {

String saveOrder(Order order);

String updateOrder(Order order);

String deleteOrderById(int id);

Order getOrderById(int id);

}
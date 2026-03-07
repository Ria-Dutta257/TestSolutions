package com.example.main;

import java.time.LocalDate;
import java.util.List;

import com.example.dao.CustomerDAO;
import com.example.dao.OrderDAO;
import com.example.dao.impl.CustomerDAOImpl;
import com.example.dao.impl.OrderDAOImpl;
import com.example.entity.Customer;
import com.example.entity.Order;

public class MainApp {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        System.out.println("----------- INSERT 10 CUSTOMERS -----------");

        for (int i = 1; i <= 10; i++) {

            Customer customer = new Customer();
            customer.setCustomerName("Customer" + i);
            customer.setEmail("customer" + i + "@gmail.com");
            customer.setGender("Female");
            customer.setPhone(9000000000L + i);
            customer.setRegistrationDate(LocalDate.now());

            Order order = new Order();
            order.setOrderNumber("ORD10" + i);
            order.setProductName("Laptop" + i);
            order.setQuantity(i);
            order.setPrice(50000 + i * 1000);
            order.setOrderDate(LocalDate.now());

            customer.setOrder(order);

            customerDAO.saveCustomer(customer);
        }

        System.out.println("10 Customers inserted successfully\n");

        System.out.println("----------- FETCH CUSTOMER BY ID -----------");

        Customer c = customerDAO.getCustomerById(1);

        System.out.println("Name: " + c.getCustomerName());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Product: " + c.getOrder().getProductName());

        System.out.println("\n----------- FETCH ALL CUSTOMERS -----------");

        List<Customer> list = customerDAO.getAllCustomers();

        for (Customer cust : list) {

            System.out.println("ID: " + cust.getId());
            System.out.println("Name: " + cust.getCustomerName());
            System.out.println("Email: " + cust.getEmail());
            System.out.println("Product: " + cust.getOrder().getProductName());
            System.out.println("--------------------------");
        }

        System.out.println("\n----------- UPDATE CUSTOMER -----------");

        Customer updateCustomer = customerDAO.getCustomerById(2);
        updateCustomer.setPhone(9999999999L);

        customerDAO.updateCustomer(updateCustomer);

        System.out.println("Customer updated successfully");

        System.out.println("\n----------- JPQL FETCH BY EMAIL -----------");

        Customer emailCustomer = customerDAO.getCustomerByEmail("customer3@gmail.com");

        System.out.println("Customer Name: " + emailCustomer.getCustomerName());

        System.out.println("\n----------- UPDATE ORDER -----------");

        Order order = orderDAO.getOrderById(3);

        order.setPrice(90000);

        orderDAO.updateOrder(order);

        System.out.println("Order price updated");

        System.out.println("\n----------- DELETE CUSTOMER -----------");

        String result = customerDAO.deleteCustomerById(5);

        System.out.println(result);

    }
}
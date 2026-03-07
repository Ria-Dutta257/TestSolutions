package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="customer")
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String customerName;
private String email;
private String gender;
private long phone;

private LocalDate registrationDate;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="order_id")
private Order order;

public Customer(){}

public int getId(){ return id; }

public void setId(int id){ this.id=id; }

public String getCustomerName(){ return customerName; }

public void setCustomerName(String customerName){ this.customerName=customerName; }

public String getEmail(){ return email; }

public void setEmail(String email){ this.email=email; }

public String getGender(){ return gender; }

public void setGender(String gender){ this.gender=gender; }

public long getPhone(){ return phone; }

public void setPhone(long phone){ this.phone=phone; }

public LocalDate getRegistrationDate(){ return registrationDate; }

public void setRegistrationDate(LocalDate registrationDate){ this.registrationDate=registrationDate; }

public Order getOrder(){ return order; }

public void setOrder(Order order){ this.order=order; }

}

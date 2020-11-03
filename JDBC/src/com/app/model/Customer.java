package com.app.model;

public class Customer{
	public int id;
	public String name;
	public String phone;
	public String email;
	public double temperature;
	public String entryDateTime;
	public String exitDateTime;
	
	public Customer(){
		
	}
	
	public Customer(String name, String phone, String email, double temperature, String entryDateTime, String exitDateTime ) {
		this.name=name;
		this.phone=phone;
		this.email=email;
		this.temperature=temperature;
		this.entryDateTime=entryDateTime;
		this.exitDateTime=exitDateTime;
	}
		
	@Override
	public String toString(){
			return "Customer [id="+ id+ ", name=" + name + ", phone=" + phone + ", email=" + email + ", temperature=" + temperature + ", entryDateTime=" + entryDateTime + ", exitDateTime=" + exitDateTime + "]";
		}
	
}


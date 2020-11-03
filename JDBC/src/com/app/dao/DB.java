package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import com.app.model.Customer;


public class DB {
	
	Connection connection;
	Statement statement;
	PreparedStatement prepStmt;

	public DB() {
		try {
			Class.forName("com.my.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	}
	
	public void createConnection() {
		try {
			String url= "jdbc:mysql://localhost/APP";
			String user="root";
			String password="";
			
			connection= DriverManager.getConnection(url,user,password);
			System.out.println("2. Connection Created ");			
			
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	
	}
	public String addCustomer(Customer customer){
		String message="NA";
		/*try {
			String sql= "insert into Customer values(null, '"+customer.name+"', '"+customer.phone+"', '"+customer.email+"', '"+customer.temperature+"', '"+customer.entryDateTime+"', '"+customer.exitDateTime+"')";
			System.out.println("Sql : "+sql);
			statement = connection.createStatement(); 
			int i=statement.executeUpdate(sql);
			if(i>0) {
				message= customer.name+"Added in Database";
			}
			else {
				message= customer.name+"Not Added in Database";
			}
			
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}*/
			try {
				String sql= "insert into Customer values(null, ?,?,?,?,?,?)";
				prepStmt=connection.prepareStatement(sql);
				prepStmt.setString(1, customer.name);
				prepStmt.setString(2, customer.phone);
				prepStmt.setString(3, customer.email);
				prepStmt.setFloat(4, (float)customer.temperature);
				prepStmt.setString(5, customer.entryDateTime);
				prepStmt.setString(6, customer.exitDateTime);
				
				int i=prepStmt.executeUpdate();
				if(i>0) {
					message= customer.name+" Added in Database";
				}
				else {
					message= customer.name+" Not Added in Database";
				}
				
			}
			catch(Exception e) {
				System.out.println("Some exception"+e);
				}
		return message;
		}
	
	
	
	public String markExit(String exitDateTime, int Customerid) {
		String message= "NA";
		try {
			String sql= "update Customer set exitDateTime = ? where id = ?";
			prepStmt=connection.prepareStatement(sql);
			prepStmt.setString(1, exitDateTime);
			prepStmt.setInt(2, Customerid);
			
			int i=prepStmt.executeUpdate();
			if(i>0) {
				message= exitDateTime+" Updated in Database";
			}
			else {
				message= exitDateTime+" Not Updated in Database";
			}
			
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	return message;
	}
	
	
	public String deleteCustomer(int Customerid) {
		String message= "NA";
		try {
			String sql= "delete from Customer where id = ?";
			prepStmt=connection.prepareStatement(sql);
			//prepStmt.setString(1, exitDateTime);
			prepStmt.setInt(1, Customerid);
			
			int i=prepStmt.executeUpdate();
			if(i>0) {
				message= Customerid+" Deleted from Database";
			}
			else {
				message= Customerid+" Deleted from Database";
			}
			
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	return message;
	}
	

	public void executeBatchTransaction() {
	try {
			String sql1= "update Customer set phone='+91 73987 88371' where id =1 ";
			String sql2= "update Customer set name='Vibha' where id = 1";
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			
			statement.addBatch(sql1);
			statement.addBatch(sql2);	
			statement.executeBatch();
			connection.commit();
			System.out.println("Batch Executed");
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			
			try {
				System.out.println("Some error in batch transaction rolling back");
				connection.rollback();
				
			} catch (Exception e2) {
				System.out.println("Some exception"+e2);
			}
			}

	}
	
	public String deleteCustomerByPhone(String phone) {
		String message= "NA";
		try {
			String sql= "delete from Customer where phone = ?";
			prepStmt=connection.prepareStatement(sql);
			//prepStmt.setString(1, exitDateTime);
			prepStmt.setString(1, phone);
			
			int i=prepStmt.executeUpdate();
			if(i>0) {
				message= phone+" Deleted from Database";
			}
			else {
				message= phone+" Deleted from Database";
			}
			
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	return message;
	}
	
	public void fetchCustomerWithPhone(String phone) {
		//String message= "NA";
		try {
			
			String sql= "select * from Customer where phone = ?";
			prepStmt=connection.prepareStatement(sql);
			prepStmt.setString(1, phone);
			ResultSet resultSet = prepStmt.executeQuery(); // executeQuery() -> to fetch the data from the DataBase :) 
			
			if(resultSet.next()) {
				
				System.out.println("Customer Found");
				
				Customer customer = new Customer();
				customer.id = resultSet.getInt(1);
				customer.name = resultSet.getString(2);
				customer.phone = resultSet.getString(3);
				customer.email = resultSet.getString(4);
				customer.temperature = resultSet.getFloat(5);
				customer.entryDateTime = resultSet.getString(6);
				customer.exitDateTime = resultSet.getString(7);
				
				System.out.println(customer);
				
			}else {
				System.out.println("Customer Not Found");
			}
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
	}
	
	public ArrayList<Customer> fetchAllCustomers(){
		
		ArrayList<Customer> customers= new ArrayList<Customer>();
		try {
			
			String sql= "select * from Customer";
			prepStmt=connection.prepareStatement(sql);
			//prepStmt.setString(1, phone);
			ResultSet resultSet = prepStmt.executeQuery(); // executeQuery() -> to fetch the data from the DataBase :) 
			
			while(resultSet.next()){
				
				System.out.println("Customer Found");
				
				Customer customer = new Customer();
				customer.id = resultSet.getInt(1);
				customer.name = resultSet.getString(2);
				customer.phone = resultSet.getString(3);
				customer.email = resultSet.getString(4);
				customer.temperature = resultSet.getFloat(5);
				customer.entryDateTime = resultSet.getString(6);
				customer.exitDateTime = resultSet.getString(7);
				
				//System.out.println(customer);
				customers.add(customer);
				
				
			} 
		}
		catch(Exception e) {
			System.out.println("Some exception"+e);
			}
		return customers;
	}
	
	
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection Closed");
			
		} catch (Exception e) {
			System.out.println("Some exception"+e);
		}
	}
		
	}

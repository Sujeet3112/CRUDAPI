package com.app.model;

import org.bson.Document;

public class User {
	
	public String email;
	public String name;
	public String password;
	
	public User(String name,String email, String password) {
		this.name=name;
		this.email=email;
		this.password=password;
	}
	public User() {
		
	}
	
	public Document toDocument() {
		
		Document document= new Document();
		document.append("name",name);
		document.append("email",email);
		document.append("password",Util.encryptString(password));
		return document;

	}
	
	@Override
	public String toString() {
		return "User [ name= "+ name +" email = "+ email+" password = "+ password+ " ]";
	}

}

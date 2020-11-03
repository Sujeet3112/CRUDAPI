package com.app.main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.app.dao.DB;
import com.app.model.User;

public class App {
		
	public static void main(String[] args) {
		System.out.println("Welcome to MongoDB demo");
		//User user= new User("John", "john@abc", "1236547");
		//System.out.println(user);
		
		List<Document> documents= new ArrayList<Document>();
		documents.add(new User("Ankit", "An@bc", "365123").toDocument());
		documents.add(new User("Amanvish", "Amn@ca", "3123").toDocument());
		documents.add(new User("Antim", "Abhy@bxc", "36123").toDocument());
		documents.add(new User("Aniket", "At@bxc", "623").toDocument());
		documents.add(new User("Anil", "Anil@exc", "5123").toDocument());

		
		DB db= new DB();
		db.addManyUsers(documents);
		//db.addUserToCollection(user);
		//db.fetchAllDocuments();
		db.fetchUser("email", "Anil@exc");
	
}
}

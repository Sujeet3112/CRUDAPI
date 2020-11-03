package com.app.dao;

import java.util.List;


import org.bson.Document;

import com.app.model.User;
import com.app.model.Util;
import com.mongodb.	MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class DB {
	MongoDatabase database;
	MongoCollection<Document> collection;
	public DB() {
 
	MongoClientURI uri = new MongoClientURI(Util.URI);
	MongoClient mongoClient = new MongoClient(uri);
    database = mongoClient.getDatabase(Util.DB_NAME);
    
  //  database.listCollectionNames().cursor().forEachRemaining((String name)->System.out.println(name));
    
    collection= database.getCollection(Util.COLLECTION_USERS);
	}
	
	public void addUserToCollection(User user) {
	collection.insertOne(user.toDocument());
	System.out.println(user.name+" added to DB");
	}
	
	public void fetchAllDocuments() {
	collection.find().cursor().forEachRemaining((Document doc)->System.out.println(doc.toJson()));
	}
	
	public void addManyUsers(List<Document> documents) {
				collection.insertMany(documents);
				System.out.println(documents.size()+" Added to Users");
	}
	
	public void fetchUser(String what, String where) {
		System.out.println(collection.find(eq(what, where)).first().toJson());
	}

	public void updateUser() {
	collection.find().cursor().forEachRemaining((Document doc)->System.out.println(doc.toJson()));
	}
	
//	public void deleteUser() {
	//	collection.find().cursor().forEachRemaining((Document doc)->System.out.println(doc.toJson()));
	//	}
}

package com.app.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
		
			public static final String URI ="mongodb://Sujeet:Sujeet3112@cluster0-shard-00-00.da3dp.mongodb.net:27017,cluster0-shard-00-01.da3dp.mongodb.net:27017,cluster0-shard-00-02.da3dp.mongodb.net:27017/myapp?ssl=true&replicaSet=atlas-698lon-shard-0&authSource=admin&retryWrites=true&w=majority";
			public static final String DB_NAME ="myapp";
			public static final String COLLECTION_USERS ="users";
			public static final String COLLECTION_CUSTOMERS ="customers";
			
	public static String encryptString(String input) {
		String encryptedOutput="";
		try {
			MessageDigest digest= MessageDigest.getInstance("SHA-256");
			digest.update(input.getBytes());	
			
			encryptedOutput = new String(digest.digest());
			
		} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
		return encryptedOutput;
		
	}
}

package com.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-9-14 上午10:21:33 
 * 类说明 
 */
public class MongoDBJDBC {
	public static void main( String args[] ){
	      try{   
	          //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	          //MongoDatabase mongoDatabase = mongoClient.getDatabase("test");  
	          //System.out.println("Connect to database successfully");
	          //System.out.println("MongoDatabase inof is : "+mongoDatabase.getName());
	    	  ServerAddress serverAddress = new ServerAddress("localhost",27017);
	    	  List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	          addrs.add(serverAddress);
	          
	          MongoCredential credential = MongoCredential.createScramSha1Credential("wangkang", "test", "123456".toCharArray());  
	          List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	          credentials.add(credential); 
	          
	          MongoClient mongoClient = new MongoClient(addrs,credentials); 
	          MongoDatabase mongoDatabase = mongoClient.getDatabase("test");  
	          //mongoDatabase.createCollection("collection");
	          
	          MongoCollection<Document> collection = mongoDatabase.getCollection("collection");
	          System.out.println("collection get success!!");
	          
	          Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100).append("by", "Fly");  
	          List<Document> documents = new ArrayList<Document>();  
	          documents.add(document);  
	          collection.insertMany(documents); 
	          System.out.println("document insert success!!");
	          
	          FindIterable<Document> findIterable = collection.find();  
	          MongoCursor<Document> mongoCursor = findIterable.iterator();  
	          while(mongoCursor.hasNext()){  
	             System.out.println(mongoCursor.next());  
	          }  
	          
	          collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
	          
	          collection.deleteOne(Filters.eq("likes", 200)); 
	          collection.deleteMany (Filters.eq("likes", 200));
	      }catch(Exception e){
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	 }
}

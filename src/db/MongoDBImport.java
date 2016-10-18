package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.text.ParseException;

public class MongoDBImport {
    public static void main(String[] args) throws ParseException {
     // main allows to run alone 
   	 MongoClient mongoClient = new MongoClient();
   	 MongoDatabase db = mongoClient.getDatabase(DBUtil.DB_NAME);
   	 // if user didn't exist, will create a new one automatically
   	 db.getCollection("users").insertOne( 
   			 new Document()
   					 .append("first_name", "Feiying")
   					 .append("last_name", "Liu")
   					 .append("password", "3229c1097c00d497a0fd282d586be050")
   					 .append("user_id", "1111"));
   	 mongoClient.close();
    }
}
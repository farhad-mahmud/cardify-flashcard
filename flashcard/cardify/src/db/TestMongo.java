package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class TestMongo {
    public static MongoDatabase connect() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("test_db");
        System.out.println("Mongo connected: " + db.getName());
        return db;
    }
}

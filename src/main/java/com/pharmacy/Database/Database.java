package com.pharmacy.Database;

//MongoDB imports
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    //Verbindung zur cloud
    public static MongoClient client = new MongoClient(new MongoClientURI("mongodb+srv://heyhey:test@lovelyprojectsbypaipai.jp7fl.mongodb.net/test"));


    public static MongoDatabase database = client.getDatabase("Pharmacy");

    public static MongoCollection<Document> users = Database.database.getCollection("users");
    public static MongoCollection<Document> medicine = Database.database.getCollection("medicine");

    public static FindIterable<Document> foundedUsers = users.find();
    public static FindIterable<Document> foundedMedicine = medicine.find();
}

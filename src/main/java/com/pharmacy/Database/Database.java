package com.pharmacy.Database;

//Imports
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    //Mongo Cluster Connection
    public static MongoClient client = new MongoClient(new MongoClientURI("mongodb+srv://heyhey:test@lovelyprojectsbypaipai.jp7fl.mongodb.net/test"));

    //Recieving DB
    public static MongoDatabase database = client.getDatabase("Pharmacy");

    //Getting collections
    public static MongoCollection<Document> users = Database.database.getCollection("users");
    public static MongoCollection<Document> medicine = Database.database.getCollection("medicine");
    public static MongoCollection<Document> orders = Database.database.getCollection("orders");

    //Getting all objects of collections
    public static FindIterable<Document> foundedUsers = users.find();
    public static FindIterable<Document> foundedMedicine = medicine.find();
    public static FindIterable<Document> foundedOrders = orders.find();
}

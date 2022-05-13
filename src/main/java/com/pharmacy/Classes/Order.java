package com.pharmacy.Classes;

import org.bson.types.ObjectId;

public class Order {
    //Statements
    private ObjectId id;
    private String medicine;
    private int sum;
    private int quant;
    private String address;

    //Constructor
    public Order(ObjectId id, String medicine, int sum, int quant, String address) {
        this.id = id;
        this.medicine = medicine;
        this.sum = sum;
        this.quant = quant;
        this.address = address;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String name) {
        this.medicine = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.pharmacy.Classes;

public class Medicine {
    //Statements
    private String name;
    private int price;
    private int quant;
    private int serialNumber;
    private String date;
    private int discount;

    //Constructor without discount
    public Medicine(String name, int quant, int price, int serialNumber, String date) {
        this.name = name;
        this.price = price;
        this.quant = quant;
        this.serialNumber = serialNumber;
        this.date = date;
    }

    //Constructor with discount
    public Medicine(String name, int quant, int price, int serialNumber, String date, int discount) {
        this.name = name;
        this.price = price;
        this.quant = quant;
        this.serialNumber = serialNumber;
        this.date = date;
        this.discount = discount;
    }


    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

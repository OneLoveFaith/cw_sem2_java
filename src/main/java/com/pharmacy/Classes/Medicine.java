package com.pharmacy.Classes;

public class Medicine {
    private String name;
    private int price;
    private int quant;

    public Medicine(String name, int quant, int price) {
        this.name = name;
        this.price = price;
        this.quant = quant;
    }

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
}

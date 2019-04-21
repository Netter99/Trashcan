package org.entity;

public class Goods {
    private String name;
    private int stocks;
    private double price;
    private int buy_nums;

    public Goods() {
    }

    public Goods(String name, int stocks, double price, int buy_nums) {
        this.name = name;
        this.stocks = stocks;
        this.price = price;
        this.buy_nums = buy_nums;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBuy_nums(int buy_nums) {
        this.buy_nums = buy_nums;
    }

    public String getName() {
        return name;
    }

    public int getStocks() {
        return stocks;
    }

    public double getPrice() {
        return price;
    }

    public int getBuy_nums() {
        return buy_nums;
    }
}

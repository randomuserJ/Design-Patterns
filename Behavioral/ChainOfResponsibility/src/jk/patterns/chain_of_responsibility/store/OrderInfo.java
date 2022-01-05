package jk.patterns.chain_of_responsibility.store;

public class OrderInfo {
    private String orderInfo;
    private double price;

    public OrderInfo(String orderInfo, double price) {
        this.orderInfo = orderInfo;
        this.price = price;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public double getPrice() {
        return price;
    }
}

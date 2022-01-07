package jk.patterns.chain_of_responsibility.store;

public class OrderInfo {
    private final String orderInfo;
    private final double price;

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

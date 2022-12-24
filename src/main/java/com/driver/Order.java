package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order()
    {

    }
    public Order(String id, String deliveryTime) {

//        int len = deliveryTime.length();
//        int hrs = Integer.parseInt(deliveryTime.substring(0 , 2));
//        int min = Integer.parseInt(deliveryTime.substring(len - 2 , len));
//        int time = hrs * 60 + min;
        this.deliveryTime = Integer.parseInt(deliveryTime);// The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}

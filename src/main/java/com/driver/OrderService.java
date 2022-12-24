package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }


    public void addPartner(String partnerId , DeliveryPartner deliveryPartner){
      orderRepository.addPartner(partnerId , deliveryPartner);
    }


    public void addOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId , partnerId);
        //This is basically assigning that order to that partnerId
    }


    public Order getOrderById(String orderId){
        //order should be returned with an orderId.
        return orderRepository.getOrderById(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){
        //deliveryPartner should contain the value given by partnerId
        return orderRepository.getPartnerById(partnerId);
    }


    public int getOrderCountByPartnerId(String partnerId){
        //orderCount should denote the orders given by a partner-id
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }


    public List<Order> getOrdersByPartnerId(String partnerId){
        //orders should contain a list of orders by PartnerId
        return orderRepository.getOrdersByPartnerId(partnerId);
    }


    public List<Order> getAllOrders(){
        //Get all orders
        return orderRepository.getAllOrders();
    }


    public int getCountOfUnassignedOrders(){
        //Count of orders that have not been assigned to any DeliveryPartner
        return orderRepository.getCountOfUnassignedOrders();
    }


    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = 0;

        //countOfOrders that are left after a particular time of a DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }


    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = null;

        //Return the time when that partnerId will deliver his last delivery order.

        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }


    public void deletePartnerById(String partnerId){
        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        //Delete an order and also
        // remove it from the assigned order of that partnerId
        orderRepository.deleteOrderById(orderId);
    }
}

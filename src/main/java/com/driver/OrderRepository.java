package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class OrderRepository {
    HashMap<String , Order> orderMap = new HashMap<>();
    HashMap<String , DeliveryPartner> deliveryMap = new HashMap<>();
    HashMap<String , List<String>> deliveryOrderMapping = new HashMap<>();

    //List<String> unassignedOrder = new ArrayList<>();
    int unassignCount = 0;


    public void addOrder(Order order){
        orderMap.put(order.getId() , order);
    }


    public void addPartner(String partnerId , DeliveryPartner deliveryPartner){
        deliveryMap.put(partnerId , deliveryPartner);
    }


    public void addOrderPartnerPair(String orderId, String partnerId){
        if(deliveryOrderMapping.containsKey(partnerId))
        {
           deliveryOrderMapping.get(partnerId).add(orderId); // [1].add(2) => 101 : [1,2]
        }
        else
        {
         List<String> temp = new ArrayList<>(); // []
         temp.add(orderId); // [1]
         deliveryOrderMapping.put(partnerId , temp); // 101 : [1]
        }
        //This is basically assigning that order to that partnerId
    }


    public Order getOrderById(String orderId){
        //order should be returned with an orderId.
//        Order ans = null;
//        if(orderMap.containsKey(orderId) == false) return ans;
        return orderMap.get(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){
        //deliveryPartner should contain the value given by partnerId
        return deliveryMap.get(partnerId);
    }


    public int getOrderCountByPartnerId(String partnerId){
        //orderCount should denote the orders given by a partner-id
        return deliveryOrderMapping.get(partnerId).size();
    }


    public List<String> getOrdersByPartnerId(String partnerId){
        //orders should contain a list of orders by PartnerId
//        List<Order> ans = new ArrayList<>();
//        List<String> temp = new ArrayList<>();
//        temp = deliveryOrderMapping.get(partnerId); // [1 , 2 ,3]
//        for(int i=0; i<temp.size(); i++) {
//            ans.add(orderMap.get(temp.get(i)));
//        }
//        return ans;
        return  deliveryOrderMapping.get(partnerId);
    }


    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        //List<String> orders = new ArrayList<>();
        for(Order o : orderMap.values())
        {
            orders.add(o);
        }
//        for(String o : orderMap.keySet())
//        {
//            orders.add(o);
//        }
        //Get all orders
        return orders;
    }


    public int getCountOfUnassignedOrders(){
        //Count of orders that have not been assigned to any DeliveryPartner
        List<String> res = new ArrayList<>();
        if(unassignCount == 0) {
//            for (String o : orderMap.keySet()) {
//                if (deliveryOrderMapping.containsValue(o) == false) unassignCount++;
//            }
            for(String o: deliveryOrderMapping.keySet())
            {
                int size = deliveryOrderMapping.get(o).size();
                for(int i = 0; i<size; i++)
                    res.add(deliveryOrderMapping.get(o).get(i));
            }
            //res me sare orders dal gaye that are assigned
            int len = res.size();
            unassignCount = orderMap.size() - len;
//            for(int i=0; i<len; i++)
//            {
//                if(!orderMap.containsKey(res.get(i))) unassignedOrder.add(res.get(i));
//            }
        }
        return unassignCount;
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


    public void deletePartnerById(@PathVariable String partnerId){
        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        List<String> temp = new ArrayList<>(); //[]
        if(deliveryOrderMapping.containsKey(partnerId))
        {
            temp = deliveryOrderMapping.get(partnerId); //[1 ,2 , 3]
        }
        int len = temp.size();
        //for(int i=0; i<len; i++) unassignedOrder.add(temp.get(i));
        unassignCount = unassignCount + len;
        deliveryOrderMapping.remove(partnerId);
    }

    public void deleteOrderById(String orderId){
        //Delete an order and also
        // remove it from the assigned order of that partnerId
        orderMap.remove(orderId);
       // List<String> ans = new ArrayList<>();
        for(List<String> temp : deliveryOrderMapping.values())
        {
            int len = temp.size();
            for(int i = 0; i<len; i++)
            {
                if(temp.get(i).equals(orderId))
                {
                    temp.remove(i);
                    return;
                }
            }
        }
    }
}

package com.driver;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    HashMap<String ,Order> orderHashMap;
    HashMap<String ,DeliveryPartner> deliveryPartnerHashMap;
    HashMap<String,List<String>> pairHashMap;

    public OrderRepository() {
        orderHashMap=new HashMap<>();
        deliveryPartnerHashMap=new HashMap<>();
        pairHashMap=new HashMap<>();
    }

    public String addOrder(Order order){
        orderHashMap.put(order.getId(),order);
        return "New order added successfully";
    }

    public String addPartner(String partnerId){
        DeliveryPartner deliveryPartner= new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId,deliveryPartner);
        return "New delivery partner added successfully";
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
        //This is basically assigning that order to that partnerId
        if(pairHashMap.containsKey(partnerId)){
            pairHashMap.get(partnerId).add(orderId);
        }else{
            List<String> ls= new ArrayList<>();
            ls.add(orderId);
            pairHashMap.put(partnerId,ls);
        }
        return "New order-partner pair added successfully";
    }
    public Order getOrderById(String orderId){

        //order should be returned with an orderId.

        return orderHashMap.get(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){

        //deliveryPartner should contain the value given by partnerId

        return deliveryPartnerHashMap.get(partnerId);
    }


    public Integer getOrderCountByPartnerId( String partnerId){

        //orderCount should denote the orders given by a partner-id

        return pairHashMap.get(partnerId).size();
    }


    public List<String> getOrdersByPartnerId(String partnerId){

        //orders should contain a list of orders by PartnerId

        return pairHashMap.get(partnerId);
    }


    public List<String> getAllOrders(){
        List<String> orders = new ArrayList<>();

        for (Order order: orderHashMap.values()) orders.add(order.getId());
        //Get all orders
        return orders;
    }

    public Integer getCountOfUnassignedOrders(){
        int totalOrders=orderHashMap.size();
        int totalAssignedOrders=0;
        Set<String> assignedOrders = new HashSet<>();
        for(List<String> ls : pairHashMap.values()){
            assignedOrders.addAll(ls);
        }
        totalAssignedOrders=assignedOrders.size();
        //Count of orders that have not been assigned to any DeliveryPartner

        return totalOrders-totalAssignedOrders;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId( String time,  String partnerId){

        List<String> orderIds= pairHashMap.get(partnerId);
        int hh=Integer.valueOf(time.substring(0,2));
        int mm=Integer.valueOf(time.substring(3));
        int givenTime=hh*60+mm;
        int count=0;
        for(String id:orderIds){
            Order order=orderHashMap.get(id);
            int deliveryTime=order.getDeliveryTime();

            if(deliveryTime>givenTime){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId( String partnerId){
        String time = null;

        //Return the time when that partnerId will deliver his last delivery order.
        List<String> orderIds= pairHashMap.get(partnerId);
        int lastTime=-1;
        for(String id:orderIds){
            Order order=orderHashMap.get(id);
            int deliveryTime=order.getDeliveryTime();
            String dTS=order.getDeliveryTimeStr();
            if(lastTime<deliveryTime){
                lastTime=deliveryTime;
                time=dTS;
            }
        }
        return time;
    }

    public String deletePartnerById( String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.
        deliveryPartnerHashMap.remove(partnerId);
        pairHashMap.remove(partnerId);

        return partnerId + " removed successfully";
    }


    public String deleteOrderById( String orderId){

        //Delete an order and also
        // remove it from the assigned order of that partnerId
        orderHashMap.remove(orderId);

        for(List<String> ls : pairHashMap.values()){
            for(String id: ls){
                if(id.equals(orderId)) ls.remove(orderId);
            }
        }
        return orderId + " removed successfully";
    }
}
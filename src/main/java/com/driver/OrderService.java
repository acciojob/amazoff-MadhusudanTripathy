package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public String addOrder( Order order){

        return orderRepository.addOrder(order);
    }

    public String addPartner(String partnerId){

        return orderRepository.addPartner(partnerId);
    }

    public String addOrderPartnerPair(String orderId, String partnerId){

        //This is basically assigning that order to that partnerId
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){

        //order should be returned with an orderId.

        return orderRepository.getOrderById(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){

        //deliveryPartner should contain the value given by partnerId

        return orderRepository.getPartnerById(partnerId);
    }


    public Integer getOrderCountByPartnerId( String partnerId){

        //orderCount should denote the orders given by a partner-id

        return orderRepository.getOrderCountByPartnerId(partnerId);
    }


    public List<String> getOrdersByPartnerId(String partnerId){
        //orders should contain a list of orders by PartnerId

        return orderRepository.getOrdersByPartnerId(partnerId);
    }


    public List<String> getAllOrders(){


        //Get all orders
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
        //Count of orders that have not been assigned to any DeliveryPartner

        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId( String time,  String partnerId){

        //countOfOrders that are left after a particular time of a DeliveryPartner

        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId( String partnerId){

        //Return the time when that partnerId will deliver his last delivery order.

        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public String deletePartnerById( String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.

        return orderRepository.deleteOrderById(partnerId);
    }


    public String deleteOrderById( String orderId){

        //Delete an order and also
        // remove it from the assigned order of that partnerId

        return orderRepository.deleteOrderById(orderId);
    }
}

//package com.example.restaurantManagement.controller;
//
//import com.example.restaurantManagement.dto.OrderRequest;
//import com.example.restaurantManagement.model.Order;
//import com.example.restaurantManagement.model.OrderStatus;
//import com.example.restaurantManagement.serviceLayer.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/oc")
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/saveOrder")
//    public Order saveOrder(@RequestBody OrderRequest orderRequest) {
//        return orderService.createOrder(orderRequest.getCustomerId(), OrderStatus.valueOf(orderRequest.getStatus()), orderRequest.getItems());
//    }
//}

package com.example.restaurantManagement.controller;

import com.example.restaurantManagement.dto.OrderRequest;
import com.example.restaurantManagement.dto.OrderResponse;
import com.example.restaurantManagement.model.Order;
import com.example.restaurantManagement.model.OrderStatus;
import com.example.restaurantManagement.serviceLayer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oc")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/saveOrder")
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(
                orderRequest.getCustomerId(),
                OrderStatus.valueOf(orderRequest.getStatus()),
                orderRequest.getItems()
        );
    }
    @DeleteMapping("/cancelOrder/{id}")
    private String cancelOrder(@PathVariable Integer id){
        orderService.cancelOrder(id);
        return "Order with ID " + id + " has been cancelled";
    }
}
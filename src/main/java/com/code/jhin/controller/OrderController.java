package com.code.jhin.controller;

import com.code.jhin.model.order.Order;
import com.code.jhin.playLoad.response.ApiResponse;
import com.code.jhin.service.orderServie.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder( @Valid @RequestBody Order order) {
        try {
            orderService.save(order);
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse(true , " successfully" , order) , HttpStatus.CREATED
            );
        }catch (Exception e) {
            return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST
            );
        }
    }
}

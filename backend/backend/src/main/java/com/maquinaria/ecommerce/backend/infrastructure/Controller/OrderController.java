package com.maquinaria.ecommerce.backend.infrastructure.Controller;


import com.maquinaria.ecommerce.backend.application.service.OrderService;
import com.maquinaria.ecommerce.backend.domain.model.Order;
import com.maquinaria.ecommerce.backend.domain.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private  final  OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){


        if (order.getOrderStatus().toString().equals(OrderStatus.CANCELLED.toString()) ){
            order.setOrderStatus(OrderStatus.CANCELLED);
        }else{
            order.setOrderStatus(OrderStatus.CONFIRMED);
        }

        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping("/update/state/order")
    public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String status){
        orderService.updateStateById(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        return  ResponseEntity.ok(orderService.findAll());
    }
     @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Iterable<Order>> findUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }
}

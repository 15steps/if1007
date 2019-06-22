package br.ufpe.cin.controller;

import br.ufpe.cin.internal.OrderConfirmation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @PostMapping
    public OrderConfirmation createOrder() {
        return new OrderConfirmation();
    }

}

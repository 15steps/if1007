package br.ufpe.cin.controller;

import br.ufpe.cin.dto.OrderDTO;
import br.ufpe.cin.internal.OrderConfirmation;
import br.ufpe.cin.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public OrderConfirmation createOrder(OrderDTO orderDTO) {
        logger.info("New order received: {}", orderDTO);
        StopWatch watch = new StopWatch();
        watch.start();
        OrderConfirmation confirmation = ordersService.createOrder(orderDTO);
        logger.info("Order processed in {} ms", watch.getTotalTimeMillis());
        return confirmation;
    }

}

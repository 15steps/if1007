package br.ufpe.cin.service;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    private final OrderRepository orderRepository;

    @Autowired
    public RestaurantService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    public List<Order> getAllOrdersByRestaurantId(String id){
        return orderRepository.findAllByRestaurantId(id);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}

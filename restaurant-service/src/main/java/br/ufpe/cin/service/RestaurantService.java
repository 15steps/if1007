package br.ufpe.cin.service;

import br.ufpe.cin.internal.OrderStatus;
import br.ufpe.cin.model.OrderP;
import br.ufpe.cin.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    private final OrderRepository orderRepository;

    @Autowired
    public RestaurantService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<OrderP> getAllOrders(){
        return orderRepository.findAll();
    }


    public List<OrderP> getAllOrdersByRestaurantId(String id){
        return orderRepository.findAllByRestaurantId(id);
    }

    public void updateOrderStatus(OrderP orderNew, OrderStatus orderStatus){
        Optional<OrderP> orderOld = orderRepository.findById(orderNew.getId());

        orderOld.ifPresent(order -> {
            order.setStatus(orderStatus);
            orderRepository.save(orderOld.get());
        });
    }
}

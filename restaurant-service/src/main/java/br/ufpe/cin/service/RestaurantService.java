package br.ufpe.cin.service;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.model.RestaurantOrder;
import br.ufpe.cin.repository.OrderRepository;
import org.elasticsearch.rest.action.document.RestUpdateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OrderRepository orderRepository;


    public RestaurantService(ElasticsearchTemplate elasticsearchTemplate, OrderRepository orderRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.orderRepository = orderRepository;
    }


//    public List<Order> getAllOrders(){
//        return orderRepository.findAll();
//    }


    public List<Order> getAllOrdersByRestaurantId(String id) {
//        return orderRepository.findAllByRestaurantId(id);
        return null;
    }

    public void saveOrder(Order order) {
        orderRepository.save(convertOrder(order));
    }

    private RestaurantOrder convertOrder(Order order) {
        return RestaurantOrder.builder()
                .id(order.getId())
                .cardInfo(order.getCardInfo())
                .customerId(order.getCustomerId())
                .orderItems(order.getOrderItems())
                .restaurantId(order.getRestaurantId())
                .totalAmountDue(order.getTotalAmountDue())
                .build();
    }
}

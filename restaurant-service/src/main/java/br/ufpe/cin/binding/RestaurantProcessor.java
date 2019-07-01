package br.ufpe.cin.binding;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderStatus;
import br.ufpe.cin.model.OrderP;
import br.ufpe.cin.service.RestaurantService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class RestaurantProcessor {

    RestaurantService restaurantService;

    @StreamListener(Sink.INPUT)
    public void handleOrderAccepted(Message<Order> orderMessage) {
        OrderP orderP = new OrderP(orderMessage.getPayload().getId(),
                orderMessage.getPayload().getCustomerId(),
                orderMessage.getPayload().getRestaurantId(),
                orderMessage.getPayload().getOrderItems());

        restaurantService.saveOrder(orderP);
//        restaurantService.updateOrderStatus(orderP, OrderStatus.PREPARING);
    }

}

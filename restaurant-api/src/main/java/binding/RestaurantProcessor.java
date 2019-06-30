package binding;

import br.ufpe.cin.internal.Order;
import model.OrderP;
import br.ufpe.cin.internal.OrderStatus;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import service.RestaurantService;

@EnableBinding(Source.class)
public class RestaurantProcessor {

    RestaurantService restaurantService;

    @StreamListener(Processor.INPUT)
    public Order handleOrderAccepted(Message<Order> orderMessage) {
        OrderP orderP = new OrderP(orderMessage.getPayload().getId(),
                orderMessage.getPayload().getCustomerId(),
                orderMessage.getPayload().getRestaurantId(),
                orderMessage.getPayload().getOrderItems());

        restaurantService.updateOrderStatus(orderP, OrderStatus.PREPARING);

        System.out.println(orderMessage.getPayload().getOrderItems());
        throw new RuntimeException("An error occurred while processing payment for order " + orderMessage.getPayload().getId());
    }

}

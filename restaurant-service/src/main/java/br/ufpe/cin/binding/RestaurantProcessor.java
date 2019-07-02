package br.ufpe.cin.binding;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.service.RestaurantService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class RestaurantProcessor {

    private RestaurantService restaurantService;

    public RestaurantProcessor(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @StreamListener(Sink.INPUT)
    public void handleOrderAccepted(Order order) {
        restaurantService.saveOrder(order);
    }

}

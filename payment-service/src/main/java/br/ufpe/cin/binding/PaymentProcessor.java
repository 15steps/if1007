package br.ufpe.cin.binding;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderStatus;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
        import org.springframework.messaging.Message;

@EnableBinding(Processor.class)
public class PaymentProcessor {

    @StreamListener(Processor.INPUT)
    @Output(Processor.OUTPUT)
    public Order handlePayment(Message<Order> orderMessage) {
        System.out.println(orderMessage.getHeaders());
        throw new RuntimeException("An error occurred while processing payment for order " + orderMessage.getPayload().getId());
    }

    @StreamListener("errorChannel")
    public void handleError(Message<Order> message) {
        message.getPayload().setStatus(OrderStatus.DELIVERED);
        System.err.println("Handling error: " + message);
    }
}
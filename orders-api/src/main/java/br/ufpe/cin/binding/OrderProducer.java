package br.ufpe.cin.binding;

import br.ufpe.cin.internal.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class OrderProducer {
    private Source source;

    public OrderProducer(Source source) {
        this.source = source;
    }

    public void publish(Order order) {
        source.output().send(
                MessageBuilder.withPayload(order)
                        .setHeader("order-id", order.getId())
                        .build());
    }
}

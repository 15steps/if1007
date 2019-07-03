package br.ufpe.cin.binding;

import br.ufpe.cin.internal.Order;
import br.ufpe.cin.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

@EnableBinding(Processor.class)
public class PaymentProcessor {

    private final PaymentService paymentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PaymentProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @StreamListener(Processor.INPUT)
    @Output(Processor.OUTPUT)
    public Order handlePayment(Order order) {
        logger.info("Processing payment for order: {}", order);
        return paymentService.process(order);
    }

}

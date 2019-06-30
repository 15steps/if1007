package br.ufpe.cin.service;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RedisTemplate<String, Boolean> redisTemplate;

    @Before
    public void clearRedis() {
        redisTemplate.delete(paymentService.processNumber("4686.8642.9561.5910"));
        redisTemplate.delete(paymentService.processNumber("4686.8642.9561.5911"));
    }

    @Test
    public void testValidCard(){
        Order order = Order
                .builder()
                .cardInfo(new CardInfo("4686.8642.9561.5910", "10/2020"))
                .status(OrderStatus.PAYMENT_PENDING)
                .build();

        Order orderAux = paymentService.process(order);
        Assert.assertTrue(orderAux.getStatus() == OrderStatus.PAYMENT_ACCEPTED);

        Order orderCached = paymentService.process(order);
        Assert.assertTrue(orderCached.getStatus() == OrderStatus.PAYMENT_ACCEPTED);
    }

    @Test
    public void testInvalidCard(){
        Order order = Order
                .builder()
                .cardInfo(new CardInfo("4686.8642.9561.5911", "10/2020"))
                .status(OrderStatus.PAYMENT_PENDING)
                .build();

        Order orderAux = paymentService.process(order);
        Assert.assertTrue(orderAux.getStatus() == OrderStatus.PAYMENT_DENIED);

        Order orderCached = paymentService.process(order);
        Assert.assertTrue(orderCached.getStatus() == OrderStatus.PAYMENT_DENIED);
    }

}

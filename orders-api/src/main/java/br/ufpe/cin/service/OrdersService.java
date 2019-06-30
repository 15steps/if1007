package br.ufpe.cin.service;

import br.ufpe.cin.binding.OrderProducer;
import br.ufpe.cin.dto.OrderDTO;
import br.ufpe.cin.internal.Order;
import br.ufpe.cin.internal.OrderConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class OrdersService {
    private final Logger logger = LoggerFactory.getLogger(OrdersService.class);
    private final OrderProducer producer;

    public OrdersService(OrderProducer producer) {
        this.producer = producer;
    }

    public OrderConfirmation createOrder(OrderDTO orderDTO) {
        String uuid = generateUUID();
        Order order = Order.builder()
                .id(uuid)
                .customerId(orderDTO.getCustomerId())
                .restaurantId(orderDTO.getRestaurantId())
                .orderItems(orderDTO.getItems())
                .build();
        producer.publish(order);
        return OrderConfirmation
                .builder()
                .orderId(uuid)
                .build();
    }

    private String generateUUID() {
        String randomUuid = null;
        try {
            UUID uuid = UUID.randomUUID();
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(uuid.toString().getBytes(StandardCharsets.UTF_8));
            randomUuid = bytesToHash(salt.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while generating Order UUID", e);
        }
        return randomUuid;
    }

    private String bytesToHash(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}

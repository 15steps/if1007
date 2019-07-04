package br.ufpe.cin.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static br.ufpe.cin.internal.OrderStatus.PAYMENT_PENDING;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private String id;
    private String customerId;
    private String restaurantId;
    private CardInfo cardInfo;
    private List<OrderItem> orderItems;
    @Builder.Default
    private OrderStatus status = PAYMENT_PENDING;
    private Double totalAmountDue;
    private Times times;
}
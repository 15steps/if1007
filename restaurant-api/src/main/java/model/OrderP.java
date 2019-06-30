package model;

import br.ufpe.cin.internal.OrderItem;
import br.ufpe.cin.internal.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

import static br.ufpe.cin.internal.OrderStatus.PAYMENT_PENDING;

@Entity
public class OrderP {
    @Id
    private String id;
    private String customerId;
    private String restaurantId;
    private List<OrderItem> orderItems;
    private OrderStatus status = PAYMENT_PENDING;

    public OrderP() {
    }

    public OrderP(String id, String customerId, String restaurantId, List<OrderItem> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

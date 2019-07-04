package br.ufpe.cin.model;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.OrderItem;
import br.ufpe.cin.internal.OrderStatus;
import br.ufpe.cin.internal.Times;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "orders_index", type = "order")
@Data
@Builder
public class RestaurantOrder {
    @Id
    private String id;
    private String customerId;
    private String restaurantId;
    private CardInfo cardInfo;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private Double totalAmountDue;
    private @Field(type = FieldType.Nested, store = true)
    Times times;
}

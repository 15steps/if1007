package br.ufpe.cin.dto;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String customerId;
    private String restaurantId;
    private List<OrderItem> items;
    private CardInfo cardInfo;
}

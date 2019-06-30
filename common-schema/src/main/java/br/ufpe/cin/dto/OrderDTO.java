package br.ufpe.cin.dto;

import br.ufpe.cin.internal.CardInfo;
import br.ufpe.cin.internal.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotBlank(message = "customerId cannot be empty or null")
    private String customerId;
    @NotBlank(message = "restaurantId cannot be empty or null")
    private String restaurantId;
    @NotNull(message = "orderItems cannot be null")
    @Size(message = "orderItems cannot be empty")
    private List<OrderItem> items;
    @NotNull(message = "cardInfo cannot be null")
    private CardInfo cardInfo;
}

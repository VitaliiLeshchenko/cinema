package cinema.model.dto.mapper;

import cinema.model.Order;
import cinema.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto getOrderResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setTickets(order.getTickets());
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setUserId(order.getUser().getId());
        return dto;
    }
}

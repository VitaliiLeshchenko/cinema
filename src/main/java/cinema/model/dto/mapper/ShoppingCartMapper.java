package cinema.model.dto.mapper;

import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.dto.ShoppingCartResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    public ShoppingCartResponseDto getShoppingCartResponseDto(
            ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setUserEmail(shoppingCart.getUser().getEmail());
        List<Long> ticketsId = shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        dto.setTicketsId(ticketsId);
        return dto;
    }
}

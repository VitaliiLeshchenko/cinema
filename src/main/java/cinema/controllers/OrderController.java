package cinema.controllers;

import cinema.model.Ticket;
import cinema.model.User;
import cinema.model.dto.OrderMapper;
import cinema.model.dto.OrderRequestDto;
import cinema.model.dto.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        User user = userService.getById(orderRequestDto.getUserId());
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam(name = "userId") Long userId) {
        return orderService.getOrderHistory(userService.getById(userId))
                .stream()
                .map(orderMapper::getOrderResponseDto)
                .collect(Collectors.toList());
    }
}

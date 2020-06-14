package cinema.controllers;

import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.model.dto.ShoppingCartMapper;
import cinema.model.dto.ShoppingCartRequestDto;
import cinema.model.dto.ShoppingCartResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addmoviesession")
    public void addMovieSessionInCart(@RequestBody ShoppingCartRequestDto cartRequestDto) {
        MovieSession movieSession = movieSessionService.getById(cartRequestDto.getSessionId());
        User user = userService.getById(cartRequestDto.getUserId());
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getShoppingCartByUserId(
            @RequestParam(name = "userId") Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.getById(userId));
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCart);
    }
}
package cinema.controllers;

import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.model.dto.ShoppingCartRequestDto;
import cinema.model.dto.ShoppingCartResponseDto;
import cinema.model.dto.mapper.ShoppingCartMapper;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add-movie-session")
    public void addMovieSessionInCart(@RequestBody @Valid ShoppingCartRequestDto cartRequestDto) {
        MovieSession movieSession = movieSessionService.getById(cartRequestDto.getSessionId());
        User user = userService.getById(cartRequestDto.getUserId());
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getShoppingCartByUserId(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCart);
    }
}

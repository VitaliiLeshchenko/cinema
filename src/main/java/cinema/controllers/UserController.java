package cinema.controllers;

import cinema.model.dto.UserRequestDto;
import cinema.model.dto.UserResponseDto;
import cinema.model.dto.mapper.UserMapper;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/by-email")
    public UserResponseDto getByEmail(@RequestBody @Valid UserRequestDto dto) {
        return userMapper.getUserResponseDto(dto);
    }
}

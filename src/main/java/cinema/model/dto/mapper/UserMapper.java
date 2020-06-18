package cinema.model.dto.mapper;

import cinema.model.User;
import cinema.model.dto.UserRequestDto;
import cinema.model.dto.UserResponseDto;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private UserService userService;

    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        return dto;
    }

    public UserResponseDto getUserResponseDto(UserRequestDto requestDto) {
        return getUserResponseDto(userService.findByEmail(requestDto.getEmail()));
    }
}

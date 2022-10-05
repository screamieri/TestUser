package it.andreascrimieri.test.mapper;

import it.andreascrimieri.test.controller.dto.UserDTO;
import it.andreascrimieri.test.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}

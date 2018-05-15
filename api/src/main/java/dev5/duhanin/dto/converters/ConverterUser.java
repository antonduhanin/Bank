package dev5.duhanin.dto.converters;

import dev5.duhanin.dto.UserDTO;
import dev5.duhanin.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterUser {
    @Autowired
    ModelMapper modelMapper;

    public User userToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    public UserDTO userToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (user.getRole() != null) {
            userDTO.setIdRole(user.getRole().getId());
        }
        userDTO.setTitleRole(user.getRole().getName());
        return userDTO;
    }
}

package dev5.duhanin.interfaces;

import dev5.duhanin.dto.UserDTO;
import dev5.duhanin.entity.User;

import java.util.List;


public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> findByUserName(String username);

    UserDTO updateUser(UserDTO userDTO);

    List<UserDTO> userListByRole(long role);

    UserDTO findById(long id);

    UserDTO userByIdCard(long idCard);

    void removeUser(long idUser);

    List<UserDTO> userList();

    public UserDTO userByEmail(String email);
}

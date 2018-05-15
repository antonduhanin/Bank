package dev5.duhanin;

import dev5.duhanin.dto.UserDTO;
import dev5.duhanin.dto.converters.ConverterUser;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.entity.Role;
import dev5.duhanin.entity.User;
import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.interfaces.UserService;
import dev5.duhanin.repository.ICardDAO;
import dev5.duhanin.repository.IRoleDAO;
import dev5.duhanin.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private ICardDAO cardDAO;
    @Autowired
    private ConverterUser converterUser;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        LOG.info("start creating user in UserService");
        LOG.trace("user dto", userDTO);
        User user = converterUser.userToEntity(userDTO);
        user.setRole(roleDAO.findOne(2l));
        user = userDAO.save(user);
        LOG.info("user saved in UserService");
        return converterUser.userToDTO(user);
    }

    public List<UserDTO> findByUserName(String username) {
        LOG.info("start finding user by username in userService");
        List<User> userlist = userDAO.findByName(username);
        return userlist.stream()
                .map(user -> converterUser.userToDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        LOG.info("start updating user in UserService");
        LOG.trace("user dto", userDTO);
        User user = new User();
        LOG.info(userDTO.getName());
        LOG.info(String.valueOf(userDTO.getId()));
        if (userDAO.findOne(userDTO.getId()) != null) {
            user = userDAO.findOne(userDTO.getId());
            user.setName(userDTO.getName());
            userDAO.save(user);
            LOG.info("user was updated in userService");
        } else {
            throw new NotFoundException("user not found in userService");
        }
        return converterUser.userToDTO(user);
    }

    public List<UserDTO> userListByRole(long idRole) {
        LOG.info("start finding users by role in UserService");
        Role role = roleDAO.findOne(idRole);
        if (role == null) {
            throw new NotFoundException("role not found in userService");
        }
        List<User> userList = roleDAO.findOne(idRole).getUserList();
        return userList.stream()
                .map(user -> converterUser.userToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        LOG.info("start finding user in UserService");
        User user = userDAO.findOne(id);
        if (user == null) {
            throw new NotFoundException("user not found in userSerivce");
        }
        return converterUser.userToDTO(user);
    }

    @Transactional
    public void removeUser(long idUser) {
        LOG.info("start deleting users in userService");
        if (userDAO.findOne(idUser) == null) {
            throw new NotFoundException("user not found in userSerivce");
        }
        userDAO.delete(idUser);
        LOG.info("user deleted in userService");

    }

    public List<UserDTO> userList() {
        LOG.info("start finding all user in UserService");
        List<User> userList = userDAO.findAll();
        return userList.stream()
                .map(user -> converterUser.userToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDTO userByIdCard(long idCard) {
        LOG.info("start finding users by card in UserService");
        User user = new User();
        Card cardFind = cardDAO.findOne(idCard);
        if (cardFind == null) {
            throw new NotFoundException("card not found un UserService");
        }
        Account account = cardFind.getAccount();
        user = account.getUser();

        return converterUser.userToDTO(user);
    }

    public UserDTO userByEmail(String email){
        User user =userDAO.findByEmail(email);
        if(user == null){
            throw new NotFoundException("user not found un UserService");
        }
        return converterUser.userToDTO(user);
    }
}

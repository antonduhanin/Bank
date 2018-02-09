package dev5.duhanin.controllers;

import dev5.duhanin.dto.*;
import dev5.duhanin.interfaces.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "userControllerAPI")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private TransactionService transactionService;


    @RequestMapping( method = RequestMethod.POST)
    @Validated
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        LOG.debug("start creating card for user");
        return userService.createUser(userDTO);
    }

    @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
    public List<UserDTO> getAllUsers() {
        return userService.userList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Validated
    public UserDTO findUserById(@PathVariable("id") long id) {
        UserDTO user = userService.findById(id);
        if (user == null) {
            user = new UserDTO();
        }
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable long id) {
        userDTO.setId(id);
        UserDTO user = userService.updateUser(userDTO);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Validated
    public void remove(@PathVariable("id") long user) {
        userService.removeUser(user);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    @Validated
    public List<UserDTO> usersByRole(@PathVariable("id") long id) {
        return userService.userListByRole(id);
    }

    @RequestMapping(path = "/{id}/accounts", method = RequestMethod.POST)
    @Validated
    public AccountDTO openAccount(@PathVariable(name = "id") long idUser) {
        return accountService.openAccount(idUser);
    }

    @RequestMapping(value = "/{id}/accounts", method = RequestMethod.GET)
    @Validated
    public List<AccountDTO> getAccountsByUser(@PathVariable("id") long idUser) {
        UserDTO user = userService.findById(idUser);
        return accountService.accountListByUser(user.getId());
    }

    @RequestMapping(value = "/{id}/cards", method = RequestMethod.GET)
    @Validated
    public List<CardDTO> getCardsByUser(@PathVariable("id") long idUser) {
        return cardService.cardListByUser(idUser);
    }

    @RequestMapping(value = "/{id}/news", method = RequestMethod.GET)
    @Validated
    public List<NewsDTO> getNewsByUser(@PathVariable("id") long idUser) {
        return newsService.newsListForUser(idUser);
    }

    @RequestMapping(path = "/{id}/transactions", method = RequestMethod.GET)
    @Validated
    public List<TransactionDTO> transactionByUser(@PathVariable("id") long id) {
        UserDTO user = userService.findById(id);
        return transactionService.transactionListByUser(user.getId());
    }
}

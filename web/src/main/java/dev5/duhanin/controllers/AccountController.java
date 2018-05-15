package dev5.duhanin.controllers;

import dev5.duhanin.dto.AccountDTO;
import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.entity.User;
import dev5.duhanin.interfaces.AccountService;
import dev5.duhanin.interfaces.CardService;
import dev5.duhanin.interfaces.UserService;
import dev5.duhanin.repository.IUserDAO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Api(value = "accountControllerAPI")
public class AccountController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Validated
    public AccountDTO findById(@PathVariable("id") long idAccount) {
        LOG.debug("start finding account");
        AccountDTO accountDTO = accountService.findAccountById(idAccount);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        accountDTO.setState(auth.getName());
        return accountDTO;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Validated
    public void closeAccount(@RequestParam(name = "id") long idAccount) {
        LOG.debug("start deleting account");
        accountService.closeAccount(idAccount);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> allAccounts() {
        LOG.debug("start output all accounts");
        return accountService.accountList();
    }

    @RequestMapping(path = "/state", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validated
    public AccountDTO changeState(@RequestParam(name = "id") String idAccount, @RequestParam(name = "state") String state) {
        LOG.debug("start changing state account" + idAccount);
        Long a = new Long(idAccount);
        return accountService.changeStateAccount(a, state);
    }

    @RequestMapping(value = "/{id}/cards", method = RequestMethod.POST)
    @Validated
    public CardDTO createCard(@PathVariable("id") long account) {
        LOG.debug("start creating account");
        return cardService.createCard(account);
    }

    @RequestMapping(value = "/{id}/cards", method = RequestMethod.GET)
    @Validated
    public List<CardDTO> getCardsByAccounts(@PathVariable("id") long idAccount) {
        LOG.debug("start output cards by account");
        return cardService.cardListByAccount(idAccount);
    }
}

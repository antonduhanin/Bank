package dev5.duhanin.controllers;

import dev5.duhanin.dto.AccountDTO;
import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.interfaces.AccountService;
import dev5.duhanin.interfaces.CardService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Validated
    public AccountDTO findById(@PathVariable("id") long idAccount) {
        LOG.debug("start finding account");
        AccountDTO accountDTO = accountService.findAccountById(idAccount);
        return accountDTO;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Validated
    public void closeAccount(@PathVariable(name = "id") long idAccount) {
        LOG.debug("start deleting account");
        accountService.closeAccount(idAccount);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> allAccounts() {
        LOG.debug("start output all accounts");
        return accountService.accountList();
    }

    @RequestMapping(path = "/{id}/{state}", method = RequestMethod.PUT)
    @Validated
    public AccountDTO changeState(@PathVariable(name = "id") long idAccount, @PathVariable(name = "state") String state) {
        LOG.debug("start changing state account");
        return accountService.changeStateAccount(idAccount, state);
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

package dev5.duhanin;

import dev5.duhanin.constants.ServiceConstants;
import dev5.duhanin.dto.AccountDTO;
import dev5.duhanin.dto.converters.ConverterAccount;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.entity.User;
import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.interfaces.AccountService;
import dev5.duhanin.interfaces.CardService;
import dev5.duhanin.repository.IAccountDAO;
import dev5.duhanin.repository.ICardDAO;
import dev5.duhanin.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private IAccountDAO accountDAO;
    @Autowired
    private CardService cardService;
    @Autowired
    private ICardDAO cardDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ConverterAccount converterAccount;


    @Transactional
    public AccountDTO openAccount(long idCustomer) {
        LOG.info("start save account in accountService");
        LOG.trace("id customer: " + String.valueOf(idCustomer));
        Account account = new Account();
        User user = userDAO.findOne(idCustomer);
        if (user == null) {
            LOG.error("user not found");
            throw new NotFoundException("user not found");

        }
        account.setUser(user);
        account.setState(ServiceConstants.CARD_STATE_LOCK);
        account.setBalance(0);
        account = accountDAO.save(account);
        LOG.info("account saved", account);
        return converterAccount.accountToDto(account);
    }

    @Transactional
    public void closeAccount(long idAccount) {
        LOG.info("start delete account in accountService");
        LOG.trace("id account: " + String.valueOf(idAccount));
        Account account = accountDAO.findOne(idAccount);
        if (account == null) {
            LOG.error("account not found");
            throw new NotFoundException("account not found");
        }
        List<Card> cardList = account.getCardList();
        if (cardList != null) {
            LOG.info("start deleting cards by account in accountService");
            cardList.forEach(card -> cardService.removeCard(card.getId()));
        }
        accountDAO.delete(idAccount);
        LOG.info("account deleted");
    }

    public List<AccountDTO> accountListByUser(long userId) {
        LOG.info("start finding accounts by user in accountService");
        LOG.trace("id account: " + String.valueOf(userId));
        List<Account> accounts = accountDAO.accountListByUser(userId);
        return accounts.stream()
                .map(account -> converterAccount.accountToDto(account))
                .collect(Collectors.toList());
    }

    public List<AccountDTO> accountList() {
        LOG.info("start finding all accounts in accountService");
        List<Account> accounts = accountDAO.findAll();
        return accounts.stream()
                .map(account -> converterAccount.accountToDto(account))
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(long idAccount) {
        LOG.info("start finding account by id in accountService");
        LOG.trace("id account: " + String.valueOf(idAccount));
        Account account = accountDAO.findOne(idAccount);
        if (account == null) {
            throw new NotFoundException("account not found");
        }
        return converterAccount.accountToDto(account);
    }

    @Transactional
    public AccountDTO changeStateAccount(long idAccount, String state) {
        LOG.info("start changing accounts state in accountService");
        LOG.trace(String.valueOf(idAccount));
        LOG.trace(state);
        Account account = accountDAO.findOne(idAccount);
        if (account == null) {
            LOG.error("account not found in accountService");
            throw new NotFoundException("account not found in accountService");
        }
        account.setState(state);
        List<Card> cardList = account.getCardList();
        if (cardList != null) {
            LOG.info("start changing cards state in accountService");
            cardList.forEach(card -> card.setState(state));
        }
        account = accountDAO.save(account);
        LOG.info("account updated");
        return converterAccount.accountToDto(account);
    }

    public AccountDTO findAccountByIdCard(long idCard) {
        LOG.info("start finding card in accountService");
        LOG.trace("id card: " + String.valueOf(idCard));
        Account account = new Account();
        Card card = cardDAO.findOne(idCard);
        if (card == null) {
            LOG.error("card not found in  account service");
            throw new NotFoundException("card not found in accountService");
        }
        account = card.getAccount();
        return converterAccount.accountToDto(account);
    }

    @Transactional
    public AccountDTO changeBalance(long idAccount, float amount) {
        LOG.info("start change balance of account accountService");
        Account account = accountDAO.findOne(idAccount);
        account.setBalance(account.getBalance() + amount);
        account = accountDAO.save(account);
        return converterAccount.accountToDto(account);
    }
}

package dev5.duhanin;

import dev5.duhanin.constants.ServiceConstants;
import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.dto.converters.ConverterCard;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.entity.User;
import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.exceptions.ServiceException;
import dev5.duhanin.interfaces.CardService;
import dev5.duhanin.repository.IAccountDAO;
import dev5.duhanin.repository.ICardDAO;
import dev5.duhanin.repository.IUserDAO;
import dev5.duhanin.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final static Logger LOG = LoggerFactory.getLogger(CardService.class);
    @Autowired
    private ICardDAO cardDAO;
    @Autowired
    private ServiceUtils serviceUtils;
    @Autowired
    private IAccountDAO accountDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ConverterCard converterCard;

    @Transactional
    public CardDTO createCard(long idAccount) {
        LOG.info("start creating card in cardService");
        Card card = new Card();
        Account account = accountDAO.findOne(idAccount);
        if (account == null) {
            throw new NotFoundException("account not found in cardService");
        }
        card.setAccount(account);
        card.setBalance(0);
        card.setState(ServiceConstants.CARD_STATE_LOCK);
        card = cardDAO.save(card);
        LOG.info("card created");
        return converterCard.cardToDTO(card);
    }

    @Transactional
    public void removeCard(long idCard) {
        LOG.info("start deleting card in cardService");
        LOG.trace(String.valueOf(idCard));
        if (cardDAO.findOne(idCard) == null) {
            throw new NotFoundException("card not found in cardService");
        }
        cardDAO.delete(idCard);
        LOG.info("card delete in cardService");
    }

    public List<CardDTO> cardListByAccount(long idAccount) {
        LOG.info("start finding cards by account in cardService");
        LOG.trace(String.valueOf(idAccount));
        Account account = accountDAO.findOne(idAccount);
        if (account == null) {
            throw new NotFoundException("account not found in cardService");
        }
        List<CardDTO> cards = account.getCardList()
                .stream()
                .map(card -> converterCard.cardToDTO(card))
                .collect(Collectors.toList());

        return cards;
    }

    public List<CardDTO> cardListByUser(long idUser) {
        LOG.info("start finding cards by user in cardService");
        LOG.trace(String.valueOf(idUser));
        User user = userDAO.findOne(idUser);
        if (user == null) {
            throw new NotFoundException("user not found in cardService");
        }
        List<Card> cardList = new ArrayList<>();
        List<Account> accountList = accountDAO.accountListByUser(idUser);
        accountList.forEach(account -> cardList.addAll(account.getCardList()));

        return cardList
                .stream()
                .map(card -> converterCard.cardToDTO(card))
                .collect(Collectors.toList());
    }

    public List<CardDTO> cardList() {
        LOG.info("start  all cards  in cardService");
        List<Card> cardList = cardDAO.findAll();
        return cardList.stream()
                .map(card -> converterCard.cardToDTO(card))
                .collect(Collectors.toList());
    }

    public CardDTO findCardById(long idCard) {
        LOG.info("start finding  card by id in cardService");
        Card card = cardDAO.findOne(idCard);
        if (card == null) {
            throw new NotFoundException("card not found in cardService");
        }
        return converterCard.cardToDTO(card);
    }

    @Transactional
    public CardDTO changeStateCard(long idCard, String state) {
        LOG.info("start finding  card by id in cardService");
        LOG.trace(String.valueOf(idCard));
        LOG.trace(state);
        Card card = cardDAO.findOne(idCard);
        if (card == null) {
            throw new NotFoundException("card not found in cardService");
        }
        card = cardDAO.findOne(idCard);
        card.setState(state);
        card = cardDAO.save(card);
        LOG.info("card was updated in cardService");
        return converterCard.cardToDTO(card);
    }

    @Transactional
    public CardDTO replenishCardFromAccount(long idCard, float amount) throws ServiceException {
        LOG.info("start replenish card from account in accountService");
        LOG.trace(String.valueOf(idCard));
        LOG.trace(String.valueOf(amount));
        Card card = cardDAO.findOne(idCard);
        if (card == null) {
            throw new NotFoundException("card not found in cardService");
        }
        Account account = card.getAccount();
        List<Card> cardList = account.getCardList();
        float cardSum = 0;
        for (Card c : cardList) {
            cardSum += c.getBalance();
        }
        float freeMoney = account.getBalance() - cardSum;
        if (freeMoney < amount) {
            LOG.error("insufficient funds");
            throw new ServiceException(ServiceConstants.REPLENISH_NOT_SECCESS);
        }
        if (serviceUtils.checkState(card.getState())) {
            LOG.error("card not UNLOCK");
            throw new ServiceException(ServiceConstants.REPLENISH_NOT_SECCESS);
        }
        card.setBalance(card.getBalance() + amount);
        card = cardDAO.save(card);
        LOG.error("card was updated cardService");

        return converterCard.cardToDTO(card);
    }

    @Transactional
    public Card changeBalance(long idCard, float amount) {
        Card card = cardDAO.findOne(idCard);
        if (serviceUtils.checkState(card.getState())) {
            return null;
        }
        card.setBalance(card.getBalance() + amount);
        return cardDAO.save(card);
    }

}

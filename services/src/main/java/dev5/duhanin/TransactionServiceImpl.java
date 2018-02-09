package dev5.duhanin;


import dev5.duhanin.dto.TransactionDTO;
import dev5.duhanin.dto.converters.ConverterTransaction;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.entity.Transaction;
import dev5.duhanin.exceptions.ServiceException;
import dev5.duhanin.interfaces.AccountService;
import dev5.duhanin.interfaces.CardService;
import dev5.duhanin.interfaces.TransactionService;
import dev5.duhanin.repository.IAccountDAO;
import dev5.duhanin.repository.ICardDAO;
import dev5.duhanin.repository.ITransactionDAO;
import dev5.duhanin.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final static Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private ITransactionDAO transactionDAO;
    @Autowired
    private CardService cardService;
    @Autowired
    private ICardDAO cardDAO;
    @Autowired
    private AccountService accountService;
    @Autowired
    private IAccountDAO accountDAO;
    @Autowired
    private ServiceUtils serviceUtils;
    @Autowired
    private ConverterTransaction converterTransaction;

    @Transactional
    public TransactionDTO transferMoneyOnCard(TransactionDTO transactionDTO) throws ServiceException {
        LOG.info("transaction on card in transaction service start");
        Transaction transaction = converterTransaction.transactionToEntity(transactionDTO);

        serviceUtils.validationCard(transaction.getCard());
        serviceUtils.checkOnBalance(transaction.getCard(), transaction.getSumma());

        Card cardRecipient = cardDAO.findOne(transactionDTO.getRecipientNumber());
        serviceUtils.validationCard(cardRecipient);

        transaction.setDate(new Date());
        transaction.setRecipient(cardRecipient.getAccount());
        transaction.setTitle("transfer on card");

        cardService.changeBalance(transaction.getCard().getId(), -transaction.getSumma());
        accountService.changeBalance(transaction.getCard().getAccount().getId(), -transaction.getSumma());

        accountService.changeBalance(transaction.getRecipient().getId(), transaction.getSumma());
        cardService.changeBalance(cardRecipient.getId(), transaction.getSumma());

        transaction = transactionDAO.save(transaction);
        LOG.info("transaction on card save");
        return converterTransaction.transactionToDTO(transaction);
    }

    @Override
    public TransactionDTO transferMoneyOnAccount(TransactionDTO transactionDTO) throws ServiceException {
        LOG.info("transaction on account in transaction service start");
        Transaction transaction = converterTransaction.transactionToEntity(transactionDTO);
        serviceUtils.validationCard(transaction.getCard());
        serviceUtils.checkOnBalance(transaction.getCard(), transaction.getSumma());

        Account accountRecipient = accountDAO.findOne(transactionDTO.getRecipientNumber());
        serviceUtils.validationAccount(accountRecipient);

        transaction.setDate(new Date());
        transaction.setRecipient(accountRecipient);
        transaction.setTitle("transfer on account");

        cardService.changeBalance(transactionDTO.getIdCard(), -transactionDTO.getSumma());
        accountService.changeBalance(transaction.getCard().getAccount().getId(), -transactionDTO.getSumma());

        accountService.changeBalance(transaction.getRecipient().getId(), transaction.getSumma());

        transaction = transactionDAO.save(transaction);
        LOG.info("transaction on account save");
        return converterTransaction.transactionToDTO(transaction);
    }

    public List<TransactionDTO> transactionListByUser(long idUser) {
        List<Account> accountList = accountDAO.accountListByUser(idUser);
        List<Card> cardList = new ArrayList<>();
        accountList.stream()
                .map(account -> cardList.addAll(account.getCardList()))
                .collect(Collectors.toList());
        List<Transaction> transactionList = new ArrayList<>();
        cardList.stream()
                .map(card -> transactionList.addAll(card.getTransactionList()))
                .collect(Collectors.toList());
        accountList.stream()
                .map(account -> transactionList.addAll(transactionDAO.transactionListByAccount(account.getId())))
                .collect(Collectors.toList());
        return transactionList.stream()
                .map(transaction -> converterTransaction.transactionToDTO(transaction))
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> transactionList() {
        List<Transaction> transactionList = transactionDAO.findAll();
        return transactionList.stream()
                .map(transaction -> converterTransaction.transactionToDTO(transaction))
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> transactionListByDate(Date startDate, Date endDate) {
        List<Transaction> transactionList = transactionDAO.findAll();
        transactionList.removeIf(transaction -> transaction.getDate().before(startDate));
        transactionList.removeIf(transaction -> transaction.getDate().after(endDate));
        return transactionList.stream()
                .map(transaction -> converterTransaction.transactionToDTO(transaction))
                .collect(Collectors.toList());
    }
}

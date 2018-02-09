package dev5.duhanin;

import dev5.duhanin.dto.TransactionDTO;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.interfaces.TransactionService;
import dev5.duhanin.repository.IAccountDAO;
import dev5.duhanin.repository.ICardDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class TransactionServiceImplTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ICardDAO cardDAO;
    @Autowired
    private IAccountDAO accountDAO;
    private final static float AMOUNT = 0.4f;
    private Card card;
    private Account account;

    @Test
    public void transferMoneyOnCard() throws Exception {
        card = cardDAO.findOne(4l);
        Card cardRecipient = cardDAO.findOne(2l);
        float expectedAmountCard = card.getBalance() - AMOUNT;
        account = card.getAccount();
        float expectedAmountAccount = account.getBalance() - AMOUNT;
        float expectedRecipientBalance = cardRecipient.getBalance() + AMOUNT;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setIdCard(4);
        transactionDTO.setRecipientNumber(2);
        transactionDTO.setSumma(AMOUNT);
        transactionService.transferMoneyOnCard(transactionDTO);

        Assert.assertEquals("decrease in balance of card  not successfully", String.format("%.2f", expectedAmountCard),
                String.format("%.2f", cardDAO.findOne(4l).getBalance()));

        Assert.assertEquals("decrease in balance of account  not successfully", String.format("%.2f", expectedAmountAccount),
                String.format("%.2f", cardDAO.findOne(4l).getAccount().getBalance()));

        Assert.assertEquals("card balance not added", String.format("%.2f", expectedRecipientBalance),
                String.format("%.2f", cardDAO.findOne(2l).getBalance()));

    }


    @Test
    public void transferMoneyOnAccount() throws Exception {
        card = cardDAO.findOne(4l);
        account = card.getAccount();
        Account accountRecipent = accountDAO.findOne(1l);
        float expectedRecipientBalance = accountRecipent.getBalance() + AMOUNT;
        float expectedAmountAccount = account.getBalance() - AMOUNT;
        float expectedAmountCard = card.getBalance() - AMOUNT;


        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setIdCard(4);
        transactionDTO.setRecipientNumber(1l);
        transactionDTO.setSumma(AMOUNT);
        transactionService.transferMoneyOnAccount(transactionDTO);

        Assert.assertEquals("decrease in balance of account  not successfully", String.format("%.2f", expectedAmountAccount),
                String.format("%.2f", accountDAO.findOne(card.getAccount().getId()).getBalance()));

        Assert.assertEquals("decrease in balance of card  not successfully", String.format("%.2f", expectedAmountCard),
                String.format("%.2f", cardDAO.findOne(4l).getBalance()));

        Assert.assertEquals("account balance not added", String.format("%.2f", expectedRecipientBalance),
                String.format("%.2f", accountDAO.findOne(1l).getBalance()));

    }

}

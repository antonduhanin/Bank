package dev5.duhanin;

import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.interfaces.AccountService;
import dev5.duhanin.interfaces.CardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;

    @Test
    public void changeStateAccount() throws Exception {
        accountService.changeStateAccount(2, "UNLOCK");
        List<CardDTO> cardList = cardService.cardListByAccount(2);
        Assert.assertEquals("account is not lock", "UNLOCK", accountService.findAccountById(2).getState());
        for (CardDTO card : cardList) {
            Assert.assertEquals("card is not lock", "UNLOCK", card.getState());
        }

    }

}
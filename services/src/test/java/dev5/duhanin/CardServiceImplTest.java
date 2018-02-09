package dev5.duhanin;

import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.entity.Card;
import dev5.duhanin.interfaces.CardService;
import dev5.duhanin.repository.ICardDAO;
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
public class CardServiceImplTest {

    @Autowired
    private CardService cardService;
    @Autowired
    private ICardDAO cardDAO;

    private List<CardDTO> cardList;

    @Test
    public void cardListByUser() throws Exception {
        cardList = cardService.cardListByUser(2);
        for (CardDTO cardDTO : cardList) {
            Card card = cardDAO.findOne(cardDTO.getId());
            Assert.assertEquals(2, card.getAccount().getUser().getId());
        }
    }

    @Test
    public void replenishCardFromAccount() throws Exception {
        float expectedSum = cardService.findCardById(2).getBalance() + 10f;
        cardService.replenishCardFromAccount(2, 10f);
        Assert.assertEquals(String.format("%.2f", expectedSum), String.format("%.2f", cardService.findCardById(2).getBalance()));
    }

}
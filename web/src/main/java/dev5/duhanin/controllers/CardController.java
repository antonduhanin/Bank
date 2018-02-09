package dev5.duhanin.controllers;

import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.exceptions.ServiceException;
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
@RequestMapping("/cards")
@Api(value = "cardControllerAPI")
public class CardController {
    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Validated
    public CardDTO findById(@PathVariable("id") long idCard) {
        LOG.debug("start finding card");
        CardDTO cardDTO = cardService.findCardById(idCard);
        return cardDTO;
    }

    @RequestMapping(value = "/{id}/replenishment/{amount}/", method = RequestMethod.POST)
    @Validated
    public CardDTO replenishCard(@PathVariable("id") long idCard, @PathVariable("amount") float amount) throws ServiceException {
        LOG.debug("start replenish card from account");
        CardDTO cardDTO = cardService.replenishCardFromAccount(idCard, amount);
        return cardDTO;
    }

    @RequestMapping(value = "/{id}/{state}", method = RequestMethod.PUT)
    @Validated
    public CardDTO changeState(@PathVariable(name = "id") long idCard, @PathVariable(name = "state") String state) {
        LOG.debug("start finding card");
        return cardService.changeStateCard(idCard, state);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Validated
    public void remove(@PathVariable(name = "id") long idCard) {
        LOG.debug("start deleting card");
        cardService.removeCard(idCard);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CardDTO> allCards() {
        LOG.debug("start outpout all cards");
        return cardService.cardList();
    }
}

package dev5.duhanin.utils;

import dev5.duhanin.constants.ServiceConstants;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ServiceUtils {
    Logger LOG = LoggerFactory.getLogger(ServiceUtils.class);

    public boolean checkState(String state) {
        if (state.equals(ServiceConstants.CARD_STATE_UNLOCK)) {
            return false;
        } else {
            return true;
        }
    }

    public void validationCard(Card card)  {
        // logger.info(card.getState());
        if (card == null) {
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + ServiceConstants.CARD_NOT_FOUND);
        }
        if (checkState(card.getState())) {
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED);
        }
        validationAccount(card.getAccount());
    }

    public void validationAccount(Account account)  {
        if (account == null) {
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + ServiceConstants.ACCOUNT_NOT_FOUND);
        }
        if (checkState(account.getState())) {
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED);
        }
    }

    public void checkOnBalance(Card card, float amount)  {
        if (card == null || card.getBalance() < amount) {
            throw new ServiceException(ServiceConstants.TRANSACTION_FAILED);
        }
    }
}

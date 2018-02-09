package dev5.duhanin.interfaces;

import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.entity.Card;

import java.util.List;


public interface CardService {
    CardDTO createCard(long idAccount);

    void removeCard(long idCard);

    List<CardDTO> cardListByAccount(long idAccount);

    List<CardDTO> cardListByUser(long idUser);

    List<CardDTO> cardList();

    CardDTO findCardById(long idCard);

    CardDTO changeStateCard(long idCard, String state);

    CardDTO replenishCardFromAccount(long idCard, float amount);

    Card changeBalance(long idCard, float amount);

}

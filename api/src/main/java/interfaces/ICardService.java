package interfaces;

import entity.Card;
import entity.User;

import java.util.List;


public interface ICardService {
    Card createCard(int idAccount);
    void removeCard(int idCard);
    List<Card> cardListByAccount(String idAccount);

}

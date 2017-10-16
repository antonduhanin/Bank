package interfaces;

import entity.Transaction;

import java.util.Date;
import java.util.List;


public interface ITransactionService {
    Transaction createTransaction(int idCard, String title, Date date);
    void transferMoneyOnCard(int idCard,float amount);
    void replenishAccount(int idAccount,float amount);
    List<Transaction> transactionListByUser(int userId);

}

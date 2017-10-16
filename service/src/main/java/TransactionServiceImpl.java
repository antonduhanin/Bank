import entity.Transaction;
import interfaces.ITransactionService;

import java.util.Date;
import java.util.List;


public class TransactionServiceImpl implements ITransactionService {
    public Transaction createTransaction(int idCard, String title, Date date) {
        return null;
    }

    public void transferMoneyOnCard(int idCard, float amount) {

    }

    public void replenishAccount(int idAccount, float amount) {

    }

    public List<Transaction> transactionListByUser(int userId) {
        return null;
    }
}

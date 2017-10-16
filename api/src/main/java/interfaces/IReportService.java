package interfaces;

import entity.Transaction;
import entity.User;

import java.util.List;


public interface IReportService {
    List<User> userListByRole(String role);
    List<Transaction> transactionListByRole(String role);

}

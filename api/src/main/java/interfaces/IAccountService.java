package interfaces;

import entity.Account;
import entity.User;

import java.util.List;


public interface IAccountService {
    Account openAccount(int idUser);
    void closeAccount(int idAccount);
    List<Account> accountListByUser(int userId);

}

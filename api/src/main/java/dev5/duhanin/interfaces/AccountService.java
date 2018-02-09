package dev5.duhanin.interfaces;

import dev5.duhanin.dto.AccountDTO;
import dev5.duhanin.entity.Account;

import java.util.List;


public interface AccountService {
    AccountDTO openAccount(long idUser);

    void closeAccount(long idAccount);

    List<AccountDTO> accountListByUser(long userId);

    List<AccountDTO> accountList();

    AccountDTO findAccountById(long idAccount);

    AccountDTO changeStateAccount(long idAccount, String state);

    AccountDTO findAccountByIdCard(long idCard);

    AccountDTO changeBalance(long idAccount, float amount);


}

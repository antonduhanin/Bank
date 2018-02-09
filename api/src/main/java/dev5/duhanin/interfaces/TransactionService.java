package dev5.duhanin.interfaces;

import dev5.duhanin.dto.TransactionDTO;

import java.util.Date;
import java.util.List;


public interface TransactionService {
    TransactionDTO transferMoneyOnCard(TransactionDTO transactionDTO);

    TransactionDTO transferMoneyOnAccount(TransactionDTO transactionDTO);

    List<TransactionDTO> transactionListByUser(long userId);

    List<TransactionDTO> transactionList();

    List<TransactionDTO> transactionListByDate(Date startDate, Date endDate);
}

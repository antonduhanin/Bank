package dev5.duhanin.dto.converters;

import dev5.duhanin.dto.TransactionDTO;
import dev5.duhanin.entity.Transaction;
import dev5.duhanin.repository.ICardDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterTransaction {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICardDAO cardDAO;

    public Transaction transactionToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction.setCard(cardDAO.findOne(transactionDTO.getIdCard()));
        return transaction;
    }

    public TransactionDTO transactionToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setSumma(transaction.getSumma());
        transactionDTO.setIdCard(transaction.getCard().getId());
        transactionDTO.setRecipientNumber(transaction.getRecipient().getId());
        transactionDTO.setTitle(transaction.getTitle());
        return transactionDTO;
    }
}

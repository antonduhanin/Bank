package dev5.duhanin.dto.converters;

import dev5.duhanin.dto.AccountDTO;
import dev5.duhanin.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterAccount {
    @Autowired
    private ModelMapper modelMapper;

    public AccountDTO accountToDto(Account account) {
        if (account == null) {
            account = new Account();
        }
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account accountToEntity(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        return account;
    }


}

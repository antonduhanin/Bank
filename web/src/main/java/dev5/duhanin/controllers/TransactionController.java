package dev5.duhanin.controllers;

import dev5.duhanin.dto.TransactionDTO;
import dev5.duhanin.exceptions.ServiceException;
import dev5.duhanin.interfaces.TransactionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Api(value = "accountControllerAPI")
public class TransactionController {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "/account", method = RequestMethod.POST)
    public TransactionDTO transactionOnAccount(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult)  {
        LOG.debug("start transaction on account");
        TransactionDTO transaction = transactionService.transferMoneyOnAccount(transactionDTO);
        return transaction;
    }

    @RequestMapping(path = "/card", method = RequestMethod.POST)
    public TransactionDTO transactionOnCard(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        LOG.debug("start transaction on card");
        TransactionDTO transaction = transactionService.transferMoneyOnCard(transactionDTO);
        return transaction;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TransactionDTO> transactions() {
        LOG.debug("start output all transactions");
        return transactionService.transactionList();
    }

    @RequestMapping(path = "/date",method = RequestMethod.GET)
    @Validated
    public List<TransactionDTO> transactionListByDate(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        LOG.debug("start output transactions by date");
        return transactionService.transactionListByDate(fromDate, toDate);
    }
}

package dev5.duhanin;

import dev5.duhanin.interfaces.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServiceBuilder {
    private IUserService userService;
    private IAccountService accountService;
    private ICardService cardService;
    private IReportService reportService;
    private INewsService newsService;
    private ITransactionService transactionService;
    private AnnotationConfigApplicationContext context;

    public ServiceBuilder() {
        context = new AnnotationConfigApplicationContext(dev5.duhanin.DataConfiguration.class);
        userService = context.getBean(IUserService.class);
        accountService = context.getBean(IAccountService.class);
        cardService = context.getBean(ICardService.class);
        reportService = context.getBean(IReportService.class);
        newsService = context.getBean(INewsService.class);
        transactionService = context.getBean(ITransactionService.class);
    }

    public void close() {
        context.close();
    }

    public IUserService getUserService() {
        return userService;
    }

    public IAccountService getAccountService() {
        return accountService;
    }

    public ICardService getCardService() {
        return cardService;
    }

    public IReportService getReportService() {
        return reportService;
    }

    public INewsService getNewsService() {
        return newsService;
    }

    public ITransactionService getTransactionService() {
        return transactionService;
    }
}

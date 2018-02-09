package dev5.duhanin.menu;

import dev5.duhanin.ServiceBuilder;
import dev5.duhanin.entity.News;
import dev5.duhanin.entity.Transaction;
import dev5.duhanin.interfaces.IAccountService;
import dev5.duhanin.interfaces.ICardService;
import dev5.duhanin.interfaces.INewsService;
import dev5.duhanin.interfaces.ITransactionService;

import java.util.List;


public class AdministratorMenu extends GeneralMenu {

    private int administratorId;
    private INewsService newsService;

    public AdministratorMenu(ServiceBuilder serviceBuilder, int administratorId) {
        super(serviceBuilder);
        this.administratorId = administratorId;
        newsService = serviceBuilder.getNewsService();
    }

    public void operations() {
        boolean repeat = true;
        do {
            System.out.println("1: publish News");
            System.out.println("2: remove News");
            System.out.println("3: change state card");
            System.out.println("4: change state account");
            System.out.println("5: remove account");
            System.out.println("6: remove card");
            System.out.println("7: show all cards");
            System.out.println("8: show all accounts");
            System.out.println("9: show all transactions");
            System.out.println("10: exit");

            switch (inputIntNumber()) {
                case 1:
                    this.publishNews();
                    break;
                case 2:
                    removeNews();
                    break;
                case 3:
                    changeStateCard();
                    break;
                case 4:
                    changeStateAccount();
                    break;
                case 5:
                    removeAccount();
                    break;
                case 6:
                    removeCard();
                    break;
                case 7:
                    showAllCards();
                    break;
                case 8:
                    showAllAccounts();
                    break;
                case 9:
                    showAllTransactions();
                    break;
                case 10:
                    repeat = false;
                    break;
                default:
                    System.out.println("repeat selection");
            }
        } while (repeat == true);
    }

    private void showAllTransactions() {
        ITransactionService transactionService = serviceBuilder.getTransactionService();
        List<Transaction> transactionList = transactionService.transactionList();
        for (Transaction t : transactionList) {
            System.out.println(t.toString());
        }
    }

    private void changeStateAccount() {
        IAccountService accountService = serviceBuilder.getAccountService();
        showAllAccounts();
        System.out.println("select Account id");
        int idAccount = inputIntNumber();
        boolean repeat = false;
        do {
            System.out.println("select state Account");
            System.out.println("1: LOCK");
            System.out.println("2: UNLOCK");
            switch (inputIntNumber()) {
                case 1:
                    System.out.println(accountService.changeStateAccount(idAccount, "LOCK").toString());
                    break;
                case 2:
                    System.out.println(accountService.changeStateAccount(idAccount, "UNLOCK").toString());
                    break;
                default:
                    System.out.print("repeat selection");
                    repeat = true;
            }
        } while (repeat == true);
    }

    private void removeCard() {
        showAllCards();
        System.out.println("select id card");
        ICardService cardService = serviceBuilder.getCardService();
        cardService.removeCard(inputIntNumber());
    }

    private void removeAccount() {
        showAllAccounts();
        System.out.println("select id accout");
        IAccountService accountService = serviceBuilder.getAccountService();
        accountService.closeAccount(inputIntNumber());
    }

    private void changeStateCard() {
        ICardService cardService = serviceBuilder.getCardService();
        showAllCards();
        System.out.println("select card id");
        int idCard = inputIntNumber();
        boolean repeat = false;
        do {
            System.out.println("select state card");
            System.out.println("1: LOCK");
            System.out.println("2: UNLOCK");
            switch (inputIntNumber()) {
                case 1:
                    System.out.println(cardService.changeStateCard(idCard, "LOCK").toString());
                    break;
                case 2:
                    System.out.println(cardService.changeStateCard(idCard, "UNLOCK").toString());
                    break;
                default:
                    System.out.print("repeat selection");
                    repeat = true;
            }
        } while (repeat == true);
    }

    private void showAllNews() {
        List<News> newsList = newsService.newsList();
        for (News news : newsList) {
            System.out.println(news.toString());
        }
    }

    private void removeNews() {
        showAllNews();
        System.out.println("select id news");
        newsService.removeNews(inputIntNumber());
    }

    private void publishNews() {
        boolean repeat = false;
        do {
            System.out.println("1: publish news for all: ");
            System.out.println("2: publish news for user: ");

            String title = "";
            String content = "";
            switch (inputIntNumber()) {
                case 1:
                    System.out.println("title news: ");
                    title = scanner.next();
                    System.out.println("content news: ");
                    content = scanner.next();
                    newsService.publishNewsForAll(title, content);
                    break;
                case 2:
                    System.out.println("title news: ");
                    title = scanner.next();
                    System.out.println("content news: ");
                    content = scanner.next();
                    int selectedIdRecipient = selectUserByRole(2);
                    newsService.publishNewsForUser(title, content, selectedIdRecipient);
                    break;
                default:
                    System.out.print("repeat selection");
                    repeat = true;
            }
        } while (repeat == true);
    }
}

package dev5.duhanin.menu;

import dev5.duhanin.ServiceBuilder;
import dev5.duhanin.entity.Account;
import dev5.duhanin.entity.Card;
import dev5.duhanin.entity.News;
import dev5.duhanin.exceptions.ServiceException;
import dev5.duhanin.interfaces.IAccountService;
import dev5.duhanin.interfaces.ICardService;
import dev5.duhanin.interfaces.INewsService;
import dev5.duhanin.interfaces.ITransactionService;

import java.util.List;
import java.util.Scanner;


public class CustomerMenu extends GeneralMenu {

    private int customerId;


    public CustomerMenu(ServiceBuilder serviceBuilder, int customerId) {
        super(serviceBuilder);
        this.customerId = customerId;
    }

    public void operations() {
        boolean repeat = true;
        do {
            System.out.println("1: show news");
            System.out.println("2: show accounts");
            System.out.println("3: show cards");
            System.out.println("4: show transaction ");
            System.out.println("5: open account");
            System.out.println("6: create card");
            System.out.println("7: transfer money on card");
            System.out.println("8: transfer money on account");
            System.out.println("9: replenish my card");
            System.out.println("10: exit");
            switch (inputIntNumber()) {
                case 1:
                    showNews();
                    break;
                case 2:
                    this.showAccountList();
                    break;
                case 3:
                    this.showCardList();
                    break;
                case 4:
                    this.showTransactionList(customerId);
                    break;
                case 5:
                    openAccount();
                    break;
                case 6:
                    createCard();
                    break;
                case 7:
                    transferMoneyOnCard();
                    break;
                case 8:
                    transferMoneyOnAccount();
                    break;
                case 9:
                    replenishCard();
                    break;
                case 10:
                    repeat = false;
                    break;
                default:
                    System.out.println("repeat selection");
            }
        } while (repeat == true);
    }

    private void replenishCard() {
        ICardService cardService = serviceBuilder.getCardService();
        showCardList();
        System.out.println("select id card");
        int idCard = inputIntNumber();
        IAccountService accountService = serviceBuilder.getAccountService();
        System.out.println(accountService.findAccountByIdCard(idCard).toString());
        System.out.println("enter amount");
        float amount = inputFloatNumber();
        Card card = null;
        try {
            card = cardService.replenishCardFromAccount(idCard, amount);
        } catch (ServiceException e) {
            System.out.println("didn't pass");
        }
    }

    private float inputFloatNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextFloat();
            } catch (java.util.InputMismatchException e) {
                System.out.println("try again");
                scanner.nextLine();
            }
        }
    }

    private void showAccountList() {
        IAccountService accountService = serviceBuilder.getAccountService();
        List<Account> accountList = accountService.accountListByUser(customerId);
        if (accountList.size() == 0) {
            System.out.println("you haven't accounts");
        } else {
            for (Account account : accountList) {
                System.out.print(account);
            }
        }
    }

    private void showCardList() {
        ICardService cardService1 = serviceBuilder.getCardService();
        List<Card> cardList = cardService1.cardListByUser(customerId);
        if (cardList.size() == 0) {
            System.out.println("you haven't cards");
        } else {
            for (Card card : cardList) {
                System.out.print(card);
            }
        }
    }

    private void showNews() {
        INewsService newsService = serviceBuilder.getNewsService();
        List<News> news = newsService.newsListForUser(customerId);
        for (News n : news) {
            System.out.println(n.toString());
        }
    }

    private void openAccount() {
        IAccountService accountService = serviceBuilder.getAccountService();
        System.out.println("your new account: ");
        System.out.println(accountService.openAccount(customerId));
    }

    private void createCard() {
        ICardService cardService = serviceBuilder.getCardService();
        showAccountList();
        System.out.println("select your account");
        int selectedAccount = inputIntNumber();
        System.out.println("your new card: ");
        System.out.println(cardService.createCard(selectedAccount));
    }

    private void transferMoneyOnCard() {
        ITransactionService transactionService = serviceBuilder.getTransactionService();
        showCardList();
        System.out.println("select card id:");
        int idCard = inputIntNumber();
        System.out.println("enter amount: ");
        float amount = inputFloatNumber();
        showAllCards();
        System.out.println("enter  id card of recipient: ");
        int idCardRecipent = inputIntNumber();
        try {
            transactionService.transferMoneyOnCard(idCard, amount, idCardRecipent);
        } catch (ServiceException e) {
            System.out.println("transaction did not pass");
        }
    }

    private void transferMoneyOnAccount() {
        ITransactionService transactionService = serviceBuilder.getTransactionService();
        showCardList();
        System.out.println("select card id:");
        int idCard = inputIntNumber();
        System.out.println("enter amount: ");
        float amount = inputFloatNumber();
        showAllAccounts();
        System.out.println("enter  id account of recipient: ");
        int idAccountRecipent = inputIntNumber();
        try {
            transactionService.transferMoneyOnAccount(idCard, amount, idAccountRecipent);
        } catch (ServiceException e) {
            System.out.println("transaction did not pass");
        }
    }

}

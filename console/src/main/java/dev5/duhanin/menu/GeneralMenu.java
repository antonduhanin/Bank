package dev5.duhanin.menu;

import dev5.duhanin.ServiceBuilder;
import dev5.duhanin.entity.*;
import dev5.duhanin.interfaces.*;

import java.util.List;
import java.util.Scanner;

public class GeneralMenu {

    protected static Scanner scanner;
    protected static ServiceBuilder serviceBuilder;

    public GeneralMenu(ServiceBuilder serviceBuilder) {
        this.serviceBuilder = serviceBuilder;
        scanner = new Scanner(System.in);
    }

    public void selectMenu(int idRole, int idUser) {
        boolean repeat = false;
        do {
            switch ((int) idRole) {
                case 1:
                    System.out.println("Administrator's menu: ");
                    AdministratorMenu administratorMenu = new AdministratorMenu(serviceBuilder, idUser);
                    administratorMenu.operations();
                    break;
                case 2:
                    System.out.println("Customer's menu");
                    CustomerMenu customerMenu = new CustomerMenu(serviceBuilder, idUser);
                    customerMenu.operations();
                    break;
            }

        } while (repeat);
    }

    public int selectRole() {
        IReportService reportService = serviceBuilder.getReportService();
        List<Role> roleList = reportService.roleList();
        for (Role r : roleList) {
            System.out.print(r.toString());
        }
        System.out.println("select your role");
        int selectRole;
        do {
            selectRole = inputIntNumber();
            for (Role r : roleList) {
                if (r.getId() == selectRole) {
                    return selectRole;
                }
            }
            System.out.println("role not exist");
        } while (true);
    }

    public void showNewsForAll() {
        INewsService newsService = serviceBuilder.getNewsService();
        List<News> news = newsService.newsListForAll();
        for (News n : news) {
            System.out.println(n.toString());
        }
    }

    public int selectUserByRole(int selectedIdRole) {
        IUserService userService = serviceBuilder.getUserService();
        List<User> userList = userService.userListByRole(selectedIdRole);
        System.out.println("select the appropriate id");
        for (User user : userList) {
            System.out.print(user.toString());
        }
        int selectedUserId = inputIntNumber();
        if (userService.findById(selectedUserId) == null) {
            return 0;
        }
        if (userService.findById(selectedUserId).getRole().getId() != selectedIdRole) {
            return 0;
        }
        return selectedUserId;
    }

    public int inputIntNumber() {
        while (true) {
            try {
                int a = scanner.nextInt();
                return a;
            } catch (java.util.InputMismatchException e) {
                System.out.println("try again");
                scanner.nextLine();
            }
        }
    }

    public int registration(int selectedIdRole) {
        IUserService userService = serviceBuilder.getUserService();
        System.out.println("enter your name ");

        String name = scanner.next();
        User user = userService.createUser(name, selectedIdRole);
        return (int) user.getId();
    }

    protected void showAllAccounts() {
        IAccountService accountService = serviceBuilder.getAccountService();
        List<Account> accountList = accountService.accountList();
        for (Account account : accountList) {
            System.out.println(account.toString());
        }
    }

    protected void showAllCards() {
        ICardService cardService = serviceBuilder.getCardService();
        List<Card> cardlist = cardService.cardList();
        for (Card card : cardlist) {
            System.out.println(card.toString());
        }
    }

    protected void showTransactionList(int customerId) {
        ITransactionService transactionService = serviceBuilder.getTransactionService();
        List<Transaction> transactionList = transactionService.transactionListByUser(customerId);
        if (transactionList.size() == 0) {
            System.out.println("transactions not found");
        } else {
            for (Transaction transaction : transactionList) {
                System.out.print(transaction);
            }
        }
    }
}

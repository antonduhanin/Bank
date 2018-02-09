package dev5.duhanin;

import dev5.duhanin.menu.GeneralMenu;

public class Main {
    public static void main(String[] args) {
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        GeneralMenu generalMenu = new GeneralMenu(serviceBuilder);
        generalMenu.showNewsForAll();

        int selectedIdRole = generalMenu.selectRole();
        int selectedUserId = 0;
        boolean repeat;

        do {
            repeat = false;
            System.out.println("1: registration: ");
            System.out.println("2: select your user account: ");
            switch (generalMenu.inputIntNumber()) {
                case 1:
                    selectedUserId = generalMenu.registration((int) selectedIdRole);
                    break;
                case 2:
                    selectedUserId = generalMenu.selectUserByRole((int) selectedIdRole);
                    if (selectedUserId == 0) {
                        System.out.println("user not exist");
                        repeat = true;
                    }
                    break;
                default:
                    System.out.println("repeat selection");
                    repeat = true;

            }
        } while (repeat == true);

        generalMenu.selectMenu(selectedIdRole, selectedUserId);

        repeat = false;
        do {
            System.out.println("1 return to the menu");
            System.out.println("2 to exit");
            switch (generalMenu.inputIntNumber()) {
                case 1:
                    generalMenu.selectMenu((int) selectedIdRole, (int) selectedUserId);
                    repeat = true;
                    break;
                case 2:
                    repeat = false;
                    serviceBuilder.close();
                    break;
                default:
                    System.out.print("repeat selection");
                    repeat = true;
            }
        } while (repeat == true);
    }

}

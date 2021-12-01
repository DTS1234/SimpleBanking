package main.upm.simple.banking.ui;

import java.util.Scanner;

public class UIInterfaceReadImpl implements UIInterfaceRead {
    /**
     * Should return the character entered by the user.
     */
    @Override
    public String readAnInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

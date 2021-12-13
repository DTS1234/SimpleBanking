package main.upm.simple.bank;

import main.upm.simple.bank.ui.UIInterface;
import main.upm.simple.bank.ui.UIInterfaceReadImpl;

public class Main {

    public static void main(String[] args) throws Exception {
        new UIInterface(new UIInterfaceReadImpl()).run();
    }

}

package main.upm.simple.banking;

import main.upm.simple.banking.ui.UIInterface;
import main.upm.simple.banking.ui.UIInterfaceReadImpl;

public class Main {

    public static void main(String[] args) throws Exception {
        new UIInterface(new UIInterfaceReadImpl()).run();
    }

}

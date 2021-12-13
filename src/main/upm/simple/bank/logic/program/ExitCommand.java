package main.upm.simple.bank.logic.program;

public class ExitCommand {

    public void execute() {
        throw new ExitProgram();
    }

}

package main.upm.simple.banking.logic.program;

public class ExitCommand {

    public void execute() {
        throw new ExitProgram();
    }

}

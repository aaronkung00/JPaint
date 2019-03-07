package model.persistence;

import model.interfaces.ICommand;

public class RedoCommand implements ICommand {


    private static RedoCommand instance = new RedoCommand();

    private RedoCommand(){}

    public static RedoCommand getRedoCommand(){
        return instance;
    }

    @Override
    public void run() {
        CommandHistory.redo();
    }
}

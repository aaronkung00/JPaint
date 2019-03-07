package model.persistence;
import model.interfaces.ICommand;

public class UndoCommand implements ICommand {

    private static UndoCommand instance = new UndoCommand();

    private UndoCommand(){}

    public static UndoCommand getUndoCommand(){
        return instance;
    }


    @Override
    public void run() {
        CommandHistory.undo();
    }
}

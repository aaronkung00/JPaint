package main;

import model.persistence.MouseHandler;
import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import model.persistence.*;

public class Main {
    public static void main(String[] args){
        ShapeList shapeList = new ShapeList();
        PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        MouseHandler handler = new MouseHandler(appState, paintCanvas, shapeList);
        paintCanvas.addMouseListener(handler);

        IJPaintController controller = new JPaintController(uiModule, appState, handler.getShapeList(),paintCanvas);
        controller.setup();


    }
}

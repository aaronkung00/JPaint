package model.persistence;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.Stack;

public class DrawCommand implements ICommand, IUndoable {

    private ApplicationState applicationState;
    private Position position;
    private ShapeList shapeList;
    private ShapeDrawer shapeDrawer;
    private PaintCanvas paintCanvas;

    public DrawCommand(ApplicationState applicationState, Position position, ShapeList shapeList , PaintCanvas paintCanvas) {
        this.applicationState = applicationState;
        this.position = position;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
    }


    @Override
    public void run() {
        Shape shape = ShapeFactory.create(applicationState,position);
        shapeList.addShape(shape);
        shapeDrawer = new ShapeDrawer(shapeList,this.paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {

        int lastIndex = shapeList.getShapes().size() - 1 ;
        if(lastIndex >= 0)
        {
            Shape temp = null;
            try {
                temp = (Shape) shapeList.getShapes().get(lastIndex).clone();
            } catch ( CloneNotSupportedException e ) {
                e.printStackTrace();
            }
            shapeList.getDrawCachedStack().push(temp);
            Shape temp_remove_ref = this.shapeList.getShapes().get(lastIndex);
            shapeList.getShapes().remove(lastIndex);
            if(temp.isSelected)
                this.shapeList.getSelectedShapes().remove(temp_remove_ref);

        }


        this.paintCanvas.paintImmediately(this.paintCanvas.getVisibleRect());
        shapeDrawer = new ShapeDrawer(shapeList,this.paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();

    }

    @Override
    public void redo() {
        if(!this.shapeList.getDrawCachedStack().empty()) {
            Shape temp =(Shape) this.shapeList.getDrawCachedStack().pop();
            this.shapeList.getShapes().add(temp);
            if(temp.isSelected)
                this.shapeList.getSelectedShapes().add(temp);

        }

        this.paintCanvas.paintImmediately(this.paintCanvas.getVisibleRect());
        shapeDrawer = new ShapeDrawer(shapeList,this.paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();

    }
}

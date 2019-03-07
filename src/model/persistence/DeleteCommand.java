package model.persistence;

import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

public class DeleteCommand implements IUndoable {


    private static DeleteCommand instance = new DeleteCommand();
    private ShapeList cacheShapeList;
    private PaintCanvas paintCanvas;

    private DeleteCommand() {

    }


    public static DeleteCommand getDeleteInstance()
    {
        return instance;
    }

    public void delete(ShapeList shapeList, PaintCanvas paintCanvas){

        this.cacheShapeList = shapeList;
        this.paintCanvas = paintCanvas;

        for(Shape s : shapeList.getSelectedShapes())
            shapeList.removefromSelect(s);

        paintCanvas.paintImmediately(paintCanvas.getVisibleRect());
        ShapeDrawer shapeDrawer = new ShapeDrawer(this.cacheShapeList,paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();

        CommandHistory.add(this);

        System.out.println("Deleted ShapeList has - " + shapeList.getShapes().size());
    }


    @Override
    public void undo() {
        for(Shape s : this.cacheShapeList.getSelectedShapes())
            this.cacheShapeList.getShapes().add(s);

        paintCanvas.paintImmediately(paintCanvas.getVisibleRect());
        ShapeDrawer shapeDrawer = new ShapeDrawer( this.cacheShapeList,paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();
    }

    @Override
    public void redo() {
        this.delete(this.cacheShapeList,paintCanvas);
    }
}

package model.persistence;

import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

import java.awt.*;

public class PasteCommand implements IUndoable {

    private static PasteCommand instance = new PasteCommand();
    private ShapeList copiedShapeList;
    private PaintCanvas paintCanvas;


    public PasteCommand() {

    }


    public static PasteCommand getPastInstance(){
        return instance;
    }

    public void paste(ShapeList shapeList,PaintCanvas paintCanvas){

        this.copiedShapeList = shapeList;
        this.paintCanvas = paintCanvas;

        for(Shape s : shapeList.getCopiedShapesList())
        {
            int index = shapeList.getCopiedShapesList().indexOf(s);

            Shape temp = null;
            try {
                temp = (Shape) s.clone();
            } catch ( CloneNotSupportedException e ) {
                e.printStackTrace();
            }

            temp.updateOffset();
            temp.isSelected=false;
            shapeList.getShapes().add(temp);
            this.copiedShapeList.getCopiedShapesList().set(index,temp);
            this.copiedShapeList.getPasteCachedStack().push(temp);
        }
   /*     System.out.println("ShapeList - " + shapeList.getShapes().size());
        System.out.println("Selected ShapeList - "+ shapeList.getSelectedShapes().size());
        System.out.println("Copied ShapeList - " + shapeList.getCopiedShapesList().size());
    */
        ShapeDrawer shapeDrawer = new ShapeDrawer(shapeList,this.paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();


        CommandHistory.add(this);
    }


    @Override
    public void undo() {

        for(int i = 0 ; i < this.copiedShapeList.getCopiedShapesList().size() ; i++)
        {
            Shape temp = (Shape) this.copiedShapeList.getPasteCachedStack().pop();
            this.copiedShapeList.getShapes().remove(temp);
            this.copiedShapeList.getRedoPasteCachedStack().push(temp);
        }

        paintCanvas.paintImmediately(paintCanvas.getVisibleRect());
        ShapeDrawer shapeDrawer = new ShapeDrawer(copiedShapeList,paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();
    }

    @Override
    public void redo() {

        for(int i = 0 ; i < this.copiedShapeList.getCopiedShapesList().size() ; i++)
        {
            Shape temp = (Shape) this.copiedShapeList.getRedoPasteCachedStack().pop();
            this.copiedShapeList.getShapes().add(temp);
            this.copiedShapeList.getPasteCachedStack().push(temp);
        }

        paintCanvas.paintImmediately(paintCanvas.getVisibleRect());
        ShapeDrawer shapeDrawer = new ShapeDrawer(copiedShapeList,paintCanvas.getGraphics2D());
        shapeDrawer.drawShape();

    }



}

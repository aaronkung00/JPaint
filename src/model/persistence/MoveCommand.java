package model.persistence;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {

    private Position position;
    private Graphics2D graphics2D;
    private ShapeList shapeList;
    private ShapeDrawer shapeDrawer;
    private PaintCanvas paintCanvas;

    public MoveCommand(PaintCanvas paintCanvas, Position position, ShapeList shapeList, Graphics2D graphics2D) {
        this.position = position;
        this.graphics2D = graphics2D;
        this.shapeList = shapeList;
        this.paintCanvas = paintCanvas;

    }




    @Override
    public void run(){

        Rectangle detectRec = new Rectangle(position.getStartPoint().getX(),position.getStartPoint().getY(),1,1);


        for(Shape s : this.shapeList.getSelectedShapes()){

            Shape temp = null;
            try {
                temp = (Shape) s.clone();
            } catch ( CloneNotSupportedException e ) {
                e.printStackTrace();
            }
            if(detectRec.intersects(s.getBoundaryRec())) {
                int index = this.shapeList.getShapes().indexOf(s);
                int index_select = this.shapeList.getSelectedShapes().indexOf(s);
                this.shapeList.getPreMoveCachedStack().push(temp);
                this.shapeList.getindexCachedStack().push(index);
                this.shapeList.getselectIndexCachedStack().push(index_select);
                s.updateMove(this.position.getEndPoint());
                this.shapeList.getShapes().set(index,s);
                CommandHistory.add(this);

            }

        }



        this.paintCanvas.paintImmediately(this.paintCanvas.getVisibleRect());
        shapeDrawer = new ShapeDrawer(shapeList,graphics2D);
        shapeDrawer.drawShape();


    }

    @Override
    public void undo() {

        if(!this.shapeList.getPreMoveCachedStack().empty() && !this.shapeList.getindexCachedStack().empty() && ! this.shapeList.getselectIndexCachedStack().empty()) {

            Shape temp_Pre = (Shape) this.shapeList.getPreMoveCachedStack().pop();
            int index = (int) this.shapeList.getindexCachedStack().pop();
            int index_select = (int) this.shapeList.getselectIndexCachedStack().pop();
            Shape temp_cur = this.shapeList.getShapes().get(index);
            this.shapeList.getShapes().set(index, temp_Pre);
            this.shapeList.getSelectedShapes().set(index_select, temp_Pre);

            this.shapeList.getRedoStack().push(temp_cur);
            this.shapeList.getRedoIndexStack().push(index);
            this.shapeList.getRedoSelectIndexCachedStack().push(index_select);

        }

        this.paintCanvas.paintImmediately(this.paintCanvas.getVisibleRect());
        shapeDrawer = new ShapeDrawer(shapeList,graphics2D);
        shapeDrawer.drawShape();

    }

    @Override
    public void redo() {

        if(!this.shapeList.getRedoStack().empty() && !this.shapeList.getRedoIndexStack().empty() && !this.shapeList.getRedoSelectIndexCachedStack().empty())
        {
                Shape temp_Pre = (Shape) this.shapeList.getRedoStack().pop();
                int index = (int) this.shapeList.getRedoIndexStack().pop();
                int index_select = (int) this.shapeList.getRedoSelectIndexCachedStack().pop();

                Shape temp_cur =  this.shapeList.getShapes().get(index);
                this.shapeList.getShapes().set(index,temp_Pre);
                this.shapeList.getSelectedShapes().set(index_select,temp_cur);

                this.shapeList.getPreMoveCachedStack().push(temp_cur);
                this.shapeList.getindexCachedStack().push(index);
                this.shapeList.getselectIndexCachedStack().push(index_select);

        }
        this.paintCanvas.paintImmediately(this.paintCanvas.getVisibleRect());
        shapeDrawer = new ShapeDrawer(shapeList,graphics2D);
        shapeDrawer.drawShape();

    }
}

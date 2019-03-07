package model.persistence;

import model.interfaces.ICommand;

import java.awt.*;
import java.awt.Rectangle;

public class PointerSelectCommand implements ICommand {

    private Position position;
    private ShapeList shapeList;
    private boolean isCollide = false;


    public PointerSelectCommand(Position position, ShapeList shapeList) {
        this.position = position;
        this.shapeList = shapeList;
    }


    @Override
    public void run() {

        java.awt.Rectangle detectRec = new Rectangle(position.getStartPoint().getX(),position.getStartPoint().getY(),1,1);

        int collision_index = -1;

        for(Shape x : shapeList.getShapes()){
            if (detectRec.intersects(x.getBoundaryRec())){
                collision_index = this.shapeList.getShapes().indexOf(x);
                isCollide = true;
                break;
            }
        }


        if(isCollide){
            if(this.shapeList.getShapes().get(collision_index).isSelected == false)
            {
                this.shapeList.getSelectedShapes().add( this.shapeList.getShapes().get(collision_index));
                this.shapeList.getShapes().get(collision_index).isSelected  = true;
            }else
            {
                //deselect
                this.shapeList.getShapes().get(collision_index).isSelected  = false;
                this.shapeList.getSelectedShapes().remove( this.shapeList.getShapes().get(collision_index));
            }
        }
        else{

            this.shapeList.getSelectedShapes().clear();
            for (Shape s : this.shapeList.getShapes()){
                int index = this.shapeList.getShapes().indexOf(s);
                this.shapeList.getShapes().get(index).isSelected = false;
            }



        }


        for(Shape s : this.shapeList.getSelectedShapes())
            s.getinfo();


          System.out.println("Pointer - Selected List Size: " + shapeList.getSelectedShapes().size());
    }
}

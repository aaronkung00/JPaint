package model.persistence;

import model.interfaces.ICommand;

import java.awt.*;
import java.awt.Rectangle;

public class SelectCommand implements ICommand {

    private Position position;
    private ShapeList shapeList;
    private boolean isCollide = false;

    public SelectCommand( Position position, ShapeList shapeList) {
        this.position = position;
        this.shapeList = shapeList;
    }



    @Override
    public void run() {

        Rectangle detectRec = new Rectangle(position.getStartPoint().getX(),position.getStartPoint().getY(),position.getWidth(),position.getHeight());
        Rectangle clickDetectRec = new Rectangle(position.getStartPoint().getX(),position.getStartPoint().getY(),1,1);


        for(Shape x : shapeList.getShapes()){
            if (detectRec.intersects(x.getBoundaryRec()) || clickDetectRec.intersects(x.getBoundaryRec())){
                isCollide = true;
                break;
            }
        }


        if(isCollide){

            for(Shape x : shapeList.getShapes())
            {
                int index = this.shapeList.getShapes().indexOf(x);
                if ((detectRec.intersects(x.getBoundaryRec()) || clickDetectRec.intersects(x.getBoundaryRec())) && this.shapeList.getShapes().get(index).isSelected == false) {
                    x.getinfo();
                    shapeList.addSelect(x);
                    this.shapeList.getShapes().get(index).isSelected = true;
                }
            }

        }
        else{

            this.shapeList.getSelectedShapes().clear();
            for (Shape s : this.shapeList.getShapes()){
                int index = this.shapeList.getShapes().indexOf(s);
                this.shapeList.getShapes().get(index).isSelected = false;
            }

        }

        System.out.println("Selection - Now Selected List Size: " + shapeList.getSelectedShapes().size());

    }




}

package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class ShapeDrawer{

    private ShapeList shapeList ;
    private Graphics2D graphics2D;

    public  ShapeDrawer(ShapeList shapes , Graphics2D g) {
        this.shapeList = shapes;
        this.graphics2D = g;
    }

    public void drawShape(){
        for(Shape s : shapeList.getShapes()){
            IShape factory = new FindDrawingStrategyFactory(s).getShapeDrawingStrategy();
            factory.draw(graphics2D);
        }
    }


}




package model.persistence;
import model.interfaces.IShape;

import java.awt.*;

public class FindDrawingStrategyFactory implements IShape {


    private Shape shape;

    public FindDrawingStrategyFactory(Shape shape){
        this.shape = shape;
    }


    public IShape getShapeDrawingStrategy(){

        if(shape instanceof Rectangle)
        {
            switch (shape.getShadingType()) {
                case FILLED_IN:
                    return new RecFilledInStrategy( (Rectangle) shape);
                case OUTLINE:
                    return new RecOutlinedStrategy( (Rectangle) shape);
                case OUTLINE_AND_FILLED_IN:
                    return new RecOutlinedNFilledInStrategy( (Rectangle) shape);
            }
        }
        else if(shape instanceof Triangle)
        {
            switch (shape.getShadingType()) {
                case FILLED_IN:
                    return new TriangleFilledInStrategy( (Triangle) shape);
                case OUTLINE:
                    return new TriangleOutlinedStrategy( (Triangle) shape);
                case OUTLINE_AND_FILLED_IN:
                    return new TriangleOutlinedNFilledInStrategy( (Triangle) shape);
            }
        }
        else if(shape instanceof Ellipse )
        {
            switch (shape.getShadingType()) {
                case FILLED_IN:
                    return new EllipseFilledInStrategy( (Ellipse) shape);
                case OUTLINE:
                    return new EllipseOutlinedStrategy( (Ellipse) shape);
                case OUTLINE_AND_FILLED_IN:
                    return new EllipseOutlinedNFilledInStrategy( (Ellipse) shape);
            }
        }else {
            return null;
        }
        return null;
    }



    @Override
    public void draw(Graphics g) {
    }
}

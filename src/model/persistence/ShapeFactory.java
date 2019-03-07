package model.persistence;

import model.ShapeType;

public class ShapeFactory {


    public static Shape create(ApplicationState applicationState , Position position) {


        ShapeType type = applicationState.getActiveShapeType();

        switch (type) {

            case ELLIPSE:
                return new Ellipse(position,applicationState.getActivePrimaryColor()
                        ,applicationState.getActiveSecondaryColor(),applicationState.getActiveShapeShadingType());
            case RECTANGLE:
                return new Rectangle(position,applicationState.getActivePrimaryColor()
                        ,applicationState.getActiveSecondaryColor(),applicationState.getActiveShapeShadingType());
            case TRIANGLE:
                return new Triangle(position,applicationState.getActivePrimaryColor()
                        ,applicationState.getActiveSecondaryColor(),applicationState.getActiveShapeShadingType());
        }

        return null;
    }


}

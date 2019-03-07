package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.ICollision;
import model.interfaces.IMoveCommand;

public class Ellipse extends Shape implements ICollision, IMoveCommand {

    private int xPoint;
    private int yPoint;
    private int width;
    private int height;
    public Position position_Elipse;


    public Ellipse(Position position, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType) {
        super(position, primaryColor, secondaryColor, shadingType);
        this.xPoint = super.getPosition().getStartPoint().getX();
        this.yPoint = super.getPosition().getStartPoint().getY();
        this.width = super.getPosition().getWidth();
        this.height = super.getPosition().getHeight();

        this.position_Elipse = position;
    }


    public int getWidth() {
        return width;
    }

    public int getxPoint() {
        return xPoint;
    }

    public int getyPoint() {
        return yPoint;
    }

    public int getHeight() {
        return height;
    }

    //debug
    public void getinfo(){
        System.out.println("Hi! I'm Ellipse.");
        System.out.println("x_1: " + xPoint);
        System.out.println("y_1: " + yPoint);
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("Shading Type:" + super.getShadingType());
        System.out.println("Pri_Color:" + super.getPrimaryColor());
        System.out.println("Sec_Color:" + super.getSecondaryColor());
    }


    @Override
    public java.awt.Rectangle getBoundaryRec() {
        return new java.awt.Rectangle(xPoint,yPoint,width,height);
    }


    @Override
    public void updateMove(Coordinates newStartpoint) {
        this.xPoint = newStartpoint.getX();
        this.yPoint = newStartpoint.getY();
    }

    @Override
    public void updateOffset() {
        this.xPoint = this.xPoint + 5;
        this.yPoint = this.yPoint + 5;
    }


}

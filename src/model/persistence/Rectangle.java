package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.ICollision;
import model.interfaces.IMoveCommand;

public class Rectangle extends Shape implements ICollision {

    private int height;
    private int width;
    private int x_start;
    private int y_start;

    public Rectangle(Position position, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType) {
        super(position,primaryColor, secondaryColor, shadingType);
        this.height = position.getHeight();
        this.width = position.getWidth();
        this.x_start = position.getStartPoint().getX();
        this.y_start = position.getStartPoint().getY();
    }

    public int getHeight(){
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX_start() {
        return x_start;
    }

    public int getY_start() {
        return y_start;
    }

    //debug
    public void getinfo(){
        System.out.println("Hi! I'm Rectangle.");
        System.out.println("x_1: " + x_start);
        System.out.println("y_1: " + y_start);
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("Shading Type:" + super.getShadingType());
        System.out.println("Pri_Color:" + super.getPrimaryColor());
        System.out.println("Sec_Color:" + super.getSecondaryColor());

    }

    @Override
    public java.awt.Rectangle getBoundaryRec() {
        return new java.awt.Rectangle(x_start,y_start,width,height);
    }

    @Override
    public void updateMove(Coordinates newStartpoint) {
        this.x_start = newStartpoint.getX();
        this.y_start = newStartpoint.getY();
    }

    @Override
    public void updateOffset() {
        this.x_start = this.x_start + 5;
        this.y_start = this.y_start + 5;

    }


}

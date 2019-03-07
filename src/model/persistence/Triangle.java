package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.ICollision;
import model.interfaces.IMoveCommand;

import java.awt.Rectangle;

public class Triangle extends Shape implements ICollision {

    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];


    public Triangle(Position position, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType)
    {
        super(position,primaryColor, secondaryColor, shadingType);
        xPoints[0] = this.getPosition().getStartPoint().getX();
        yPoints[0] = this.getPosition().getStartPoint().getY();
        xPoints[1] = this.getPosition().getStartPoint().getX();
        yPoints[1] = this.getPosition().getEndPoint().getY();
        xPoints[2] = this.getPosition().getEndPoint().getX();
        yPoints[2] = this.getPosition().getEndPoint().getY();
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public int getSize(){
        return xPoints.length;
    }

    //debug
    public void getinfo(){
        System.out.println("Hi! I'm Tritangle.");
        System.out.println("x_1: " + xPoints[0]);
        System.out.println("y_1: " + yPoints[0]);
        System.out.println("x_2: " + xPoints[1]);
        System.out.println("y_2: " + yPoints[1]);
        System.out.println("x_3: " + xPoints[2]);
        System.out.println("y_3: " + yPoints[2]);
        System.out.println("Shading Type:" + super.getShadingType());
        System.out.println("Pri_Color:" + super.getPrimaryColor());
        System.out.println("Sec_Color:" + super.getSecondaryColor());
    }

    @Override
    public java.awt.Rectangle getBoundaryRec() {
        return new java.awt.Rectangle(xPoints[0],yPoints[0],xPoints[2]-xPoints[0],yPoints[2]-yPoints[0]);
    }


    @Override
    public void updateMove(Coordinates newStartPoint) {
        int x_diff = Math.abs(xPoints[2] - xPoints[0] );
        int y_diff = Math.abs(yPoints[1] - yPoints[0]);
        xPoints[0] = newStartPoint.getX();
        yPoints[0] = newStartPoint.getY();
        xPoints[1] = xPoints[0];
        yPoints[1] = yPoints[0] + y_diff;
        xPoints[2] = xPoints[0] + x_diff;
        yPoints[2] = yPoints[1];

    }

    @Override
    public void updateOffset() {
        xPoints[0] = xPoints[0] + 5;
        yPoints[0] = yPoints[0] + 5;
        xPoints[1] = xPoints[1] + 5;
        yPoints[1] = yPoints[1] + 5;
        xPoints[2] = xPoints[2] + 5;
        yPoints[2] = yPoints[2] + 5;
    }
}

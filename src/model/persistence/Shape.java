package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.ICollision;
import model.interfaces.IMoveCommand;
import model.interfaces.IShape;

import java.awt.*;
import java.awt.Rectangle;

public class Shape implements IShape, ICollision, IMoveCommand, Cloneable {

    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private Position position;
    public boolean isSelected = false;

    public Shape(){};

    public Shape (Position position ,ShapeColor primaryColor, ShapeColor secondaryColor , ShapeShadingType shadingType){
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType =shadingType;
        this.position = position;

    }


    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    public ShapeShadingType getShadingType() {
        return shadingType;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void draw(Graphics g) {
    }

    //debug
    public void getinfo(){

    }


    @Override
    public Rectangle getBoundaryRec() {
        return null;
    }

    @Override
    public void updateMove(Coordinates newStartPoint) {

    }

    @Override
    public void updateOffset() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

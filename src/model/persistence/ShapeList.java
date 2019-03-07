package model.persistence;

import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class ShapeList implements IShape {

    private ArrayList shapeList = new ArrayList<IShape>();
    private ArrayList selectedList = new ArrayList<IShape>();
    private ArrayList copiedList = new ArrayList<IShape>();
    private Stack drawCachedStack = new Stack<IShape>();

    private Stack PreMoveCachedStack = new Stack<IShape>();
    private Stack indexCachedStack = new Stack<IShape>();
    private Stack redoStack = new Stack<IShape>();
    private Stack redoIndexStack = new Stack<IShape>();
    private Stack selectIndexCachedStack = new Stack<IShape>();
    private Stack redoSelectIndexCachedStack = new Stack<IShape>();
    private Stack pasteCachedStack = new Stack<IShape>();
    private Stack redoPasteCachedStack = new Stack<IShape>();

    public Stack getPasteCachedStack() {
        return pasteCachedStack;
    }

    public Stack getRedoPasteCachedStack() {
        return redoPasteCachedStack;
    }

    public Stack getRedoSelectIndexCachedStack() {
        return redoSelectIndexCachedStack;
    }

    public Stack getselectIndexCachedStack() {
        return selectIndexCachedStack;
    }

    public Stack getRedoIndexStack() {
        return redoIndexStack;
    }

    public Stack getRedoStack() {
        return redoStack;
    }

    public Stack getindexCachedStack() {
        return indexCachedStack;
    }

    public Stack getPreMoveCachedStack() {
        return PreMoveCachedStack;
    }


    public void addShape(IShape shape){
        shapeList.add(shape);
    }

    public void addSelect(IShape shape){
        selectedList.add(shape);
    }

    public Stack getDrawCachedStack() {
        return drawCachedStack;
    }

    public ArrayList<Shape> getShapes() {

        return this.shapeList;
    }

    public ArrayList<Shape> getSelectedShapes() {
        return this.selectedList;
    }

    public ArrayList<Shape> getCopiedShapesList() {
        return this.copiedList;
    }

    public void addToCopy(Shape s){
        this.copiedList.add(s);
    }

    public void removefromSelect(Shape s){
        this.shapeList.remove(s);
    }


    @Override
    public void draw(Graphics g) {

    }


}

package model.persistence;

import model.interfaces.ICommand;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler extends MouseAdapter{

    private ApplicationState applicationState;
    private Position position = new Position();
    private Graphics2D graphics2D;
    private PaintCanvas paintCanvas;
    private ShapeList shapeList;
    private ICommand command = null;

    public MouseHandler( ApplicationState applicationState, PaintCanvas paintCanvas, ShapeList shapeList){
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.graphics2D = paintCanvas.getGraphics2D();

        this.shapeList = shapeList;
    }

    private void validator(Coordinates startPoint, Coordinates endPoint) {

        if (startPoint.getX() > endPoint.getX() || startPoint.getY() > endPoint.getY()) {
            this.position.setStartPoint(new Coordinates( Math.min(startPoint.getX(), endPoint.getX()) , Math.min(startPoint.getY(), endPoint.getY())));
            this.position.setEndPoint(new Coordinates( Math.max(startPoint.getX(), endPoint.getX()) , Math.max(startPoint.getY(), endPoint.getY())));
        }

    }

    public ShapeList getShapeList() {
        return shapeList;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        //Debug== System.out.println("pressed");
        position.setStartPoint(new Coordinates(e.getX(),e.getY()));
        //Debug== System.out.println(Integer.toString(position.getStartPoint().getX()) + "," + Integer.toString(position.getStartPoint().getY()));


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Debug== System.out.println("released");
        position.setEndPoint(new Coordinates(e.getX(),e.getY()));
       //Debug== System.out.println(Integer.toString(position.getEndPoint().getX()) + "," + Integer.toString(position.getEndPoint().getY()));


        switch (applicationState.getActiveStartAndEndPointMode()) {
            case DRAW:
                validator(this.position.getStartPoint(),this.position.getEndPoint());
                command = new DrawCommand(applicationState,position,shapeList,paintCanvas);
                break;
            case SELECT:
                validator(this.position.getStartPoint(),this.position.getEndPoint());
                command = new SelectCommand(position,shapeList);
                break;
          /*  case POINTER:
                command = new PointerSelectCommand(position,shapeList);
                break; */
            case MOVE:
                command = new MoveCommand(paintCanvas,position,shapeList,graphics2D);
                break;
        }

            command.run();



    }




}

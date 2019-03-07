package model.persistence;


public class Position{

    private Coordinates startPoint;
    private Coordinates endPoint;

    public Position(){

    }

    public void setEndPoint(Coordinates endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartPoint(Coordinates startPoint) {
        this.startPoint = startPoint;
    }

    public Coordinates getStartPoint(){
            return this.startPoint;
    }

    public Coordinates getEndPoint(){

        return this.endPoint;
    }

    public int getHeight(){
        return Math.abs((endPoint.getY()-startPoint.getY()));
    }

    public int getWidth(){
        return Math.abs((endPoint.getX()-startPoint.getX()));
    }


}

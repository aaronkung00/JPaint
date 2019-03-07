package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class TriangleOutlinedStrategy implements IShape {

    private Triangle tri;

    public TriangleOutlinedStrategy(Triangle tri){
        this.tri = tri;
    }


    @Override
    public void draw(Graphics g) {
        try {
            g.setColor((Color) Color.class.getField(tri.getSecondaryColor().name()).get(null));
            g.drawPolygon(tri.getxPoints(),tri.getyPoints(),tri.getSize());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

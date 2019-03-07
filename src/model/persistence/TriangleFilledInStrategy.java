package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class TriangleFilledInStrategy implements IShape {

    private Triangle tri;

    public TriangleFilledInStrategy(Triangle tri){
        this.tri = tri;
    }

    @Override
    public void draw(Graphics g) {
        try {
            g.setColor((Color) Color.class.getField(tri.getPrimaryColor().name()).get(null));
            g.fillPolygon(tri.getxPoints(),tri.getyPoints(),tri.getSize());
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}

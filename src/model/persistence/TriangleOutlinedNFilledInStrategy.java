package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class TriangleOutlinedNFilledInStrategy implements IShape {

    private Triangle tri;

    public TriangleOutlinedNFilledInStrategy(Triangle tri){
        this.tri = tri;
    }

    @Override
    public void draw(Graphics g) {
        try {
            g.setColor((Color) Color.class.getField(tri.getPrimaryColor().name()).get(null));
            g.fillPolygon(tri.getxPoints(),tri.getyPoints(),tri.getSize());
            g.setColor((Color) Color.class.getField(tri.getSecondaryColor().name()).get(null));
            g.drawPolygon(tri.getxPoints(),tri.getyPoints(),tri.getSize());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}

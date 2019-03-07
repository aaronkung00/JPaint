package model.persistence;

import model.interfaces.IShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseOutlinedStrategy implements IShape {

    private Ellipse elli;

    public EllipseOutlinedStrategy(Ellipse ellipse) {
        this.elli = ellipse;
    }

    @Override
    public void draw(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor((Color) Color.class.getField(elli.getSecondaryColor().name()).get(null));
            Ellipse2D ellipse = new Ellipse2D.Double(elli.getxPoint(),elli.getyPoint(),elli.getWidth(),elli.getHeight());
            g2.draw(ellipse);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}

package model.persistence;

import model.interfaces.IShape;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

public class EllipseFilledInStrategy implements IShape {

    private Ellipse elli;
    private Graphics2D g2 ;

    public EllipseFilledInStrategy(Ellipse ellipse) {
        this.elli = ellipse;

    }

    @Override
    public void draw(Graphics g) {
        try {
            g2 = (Graphics2D) g;
            g2.setColor((Color) Color.class.getField(elli.getPrimaryColor().name()).get(null));
            Ellipse2D ellipse = new Ellipse2D.Double(elli.getxPoint(),elli.getyPoint(),elli.getWidth(),elli.getHeight());
            g2.fill(ellipse);

        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}

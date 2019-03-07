package model.persistence;

import model.interfaces.IShape;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseOutlinedNFilledInStrategy implements IShape {

    private Ellipse elli;

    public EllipseOutlinedNFilledInStrategy(Ellipse ellipse) {
        this.elli = ellipse;
    }

    @Override
    public void draw(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D) g;
            Ellipse2D ellipseTemp = new Ellipse2D.Double(elli.getxPoint(),elli.getyPoint(),elli.getWidth(),elli.getHeight());
            g2.setColor((Color) Color.class.getField(elli.getPrimaryColor().name()).get(null));
            g2.fill(ellipseTemp);
            g2.setColor((Color) Color.class.getField(elli.getSecondaryColor().name()).get(null));
            g2.draw(ellipseTemp);
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }
}

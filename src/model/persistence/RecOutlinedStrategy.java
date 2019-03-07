package model.persistence;

import model.interfaces.IShape;
import java.awt.*;

public class RecOutlinedStrategy implements IShape {

    private final Rectangle rec;

    public RecOutlinedStrategy(Rectangle rec){
        this.rec = rec;
    }


    @Override
    public void draw(Graphics g) {
        try {
            g.setColor((Color) Color.class.getField(rec.getSecondaryColor().name()).get(null));
            g.drawRect(rec.getX_start(), rec.getY_start(), rec.getWidth(), rec.getHeight());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

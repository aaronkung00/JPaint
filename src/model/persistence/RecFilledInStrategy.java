package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class RecFilledInStrategy implements IShape {

    private Rectangle rec;

    public RecFilledInStrategy(Rectangle rec){
        this.rec = rec;
    }


    @Override
    public void draw(Graphics g) {
        try {
            g.setColor((Color) Color.class.getField(rec.getPrimaryColor().name()).get(null));
            g.fillRect(rec.getX_start(), rec.getY_start(), rec.getWidth(), rec.getHeight());
            //debug
            // System.out.format("Rec's height: %d , width: %d \n",rec.getHeight(),rec.getWidth());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

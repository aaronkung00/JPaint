package model.persistence;

import model.interfaces.IShape;

import java.awt.*;

public class RecOutlinedNFilledInStrategy implements IShape {


    private final Rectangle rec;

    public RecOutlinedNFilledInStrategy(Rectangle rec){
        this.rec = rec;
    }



    @Override
    public void draw(Graphics g) {
        try{

            new RecFilledInStrategy(rec).draw(g);
            new RecOutlinedStrategy(rec).draw(g);

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

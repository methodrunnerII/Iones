package iones;

import processing.core.PGraphics;
import processing.data.IntDict;

public class Button extends MenuObject {

    public Button(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        profile = DefaultProfile.button;
    }

    void display(PGraphics pg) {
        selectColors();
        pg.pushMatrix();
        pg.translate(x, y);
        pg.fill(cfill);
        pg.stroke(cstroke);
        pg.rect(0, 0, w, h);
        pg.popMatrix();
    }
}
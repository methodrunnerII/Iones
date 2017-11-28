package iones;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class LabelButton extends Button {
    String name;

    public LabelButton(String tn, int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        name = tn;
        if (w == 0) {
            resize(PApplet.ceil(hov.textWidth(name)) + 4, h);
        }
        if (h == 0) {
            resize(w, PApplet.ceil(hov.textAscent()) + 4);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        display(hov.g);
    }

    public void display(PGraphics pg) {
        super.display(pg);
        pg.pushMatrix();
        pg.translate(x, y);
        pg.fill(ctext);
        pg.textAlign(PConstants.CENTER, PConstants.CENTER);
        pg.text(name, w / 2, h / 2);
        pg.popMatrix();
    }
}
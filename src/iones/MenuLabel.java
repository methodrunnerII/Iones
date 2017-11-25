package iones;

import processing.core.PGraphics;
import processing.core.PImage;

class MenuLabel extends MenuObject {
    String text;
    PImage image;

    MenuLabel(String tt, PImage ti, int tx, int ty) {
        super(tx, ty, ti.width, ti.height);
        text = tt;
        image = ti;
    }

    public void setImage(PImage i) {
        image = i;
    }

    public void display() {
        display(hov.g);
    }

    public void display(PGraphics p) {
        selectColors();
        p.pushMatrix();
        p.translate(x, y);
        if (image == null) {
            p.stroke(cstroke);
            p.fill(cfill);
            p.rect(0, 0, w, h);
        } else {
            p.image(image, x, y);
        }
        p.text(text, profile.MARGIN, profile.MARGIN);
        p.popMatrix();
    }
}
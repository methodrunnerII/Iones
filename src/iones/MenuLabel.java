package iones;

import processing.core.PGraphics;
import processing.core.PImage;

public class MenuLabel extends MenuObject {
    String text;
    PImage image;

    public MenuLabel(String tt, PImage ti, int tx, int ty) {
        super(tx, ty, 0, 0);
        resize(ti.width, ti.height);
        text = tt;
        image = ti;
    }

    public MenuLabel(String tt, int x, int y, int w, int h) {
        super(x, y, w, h);
        text = tt;
    }

    public void setImage(PImage ti) {
        image = ti;
        resize(ti.width, ti.height);
    }

    public void setText(String s){
        text = s;
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
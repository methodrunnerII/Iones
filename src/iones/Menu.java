package iones;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Menu extends MenuObject {
    public String name;  //Name of the menu displayed at the top

    boolean isVolatile;  //when true, menuObject sets done = true when clicked outside
    public boolean done;

    PImage background;

    public Menu(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        profile = DefaultProfile.menu;
        done = false;
        isVolatile = false;
    }

    public void addChild(MenuObject m) {
        children.add(m);
    }

    public void reset() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).reset();
        }
    }

    public void display() {
        display(hov.g);
    }

    public void display(PGraphics pg) {
        selectColors();
        pg.pushMatrix();
        pg.translate(x, y);
        if (background != null) {
            pg.image(background, 0, 0);
        } else {
            pg.stroke(cstroke);
            pg.fill(cfill);
            pg.rectMode(PApplet.CORNER);
            pg.rect(0, 0, w, h);
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(pg);
        }
        pg.popMatrix();
    }

    public void setName(String s){ name = s; }

    public void setVolatile(boolean v){
        isVolatile = v;
    }
}
package iones;

import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PGraphics;

public class MenuObject {
    static PApplet hov;

    public static void setPApplet(PApplet p) {
        hov = p;
    }

    public ArrayList<MenuObject> children;

    public int x;
    public int y;
    public int w;
    public int h;
    int nx;  //x coordinate of following object if placed next to this one
    int ny;  //y coordinate of following object if placed under this one
    int sx;  //Screen coordinates of the object
    int sy;

    Profile profile;

    public MenuObject(int tx, int ty, int tw, int th) {
        profile = new Profile();
        children = new ArrayList<MenuObject>();

        x = tx;
        y = ty;
        w = tw;
        h = th;
        nx = x + w + profile.MARGIN;
        ny = y + h + profile.MARGIN;
    }

    void eval() {
        hov.pushMatrix();
        hov.translate(x, y);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).eval();
        }
        if(isMouseOver()){
            Iones.getCurrent().setMousedOver(this);
        }
        hov.popMatrix();
    }

    MenuObject getMouseOver(){
        MenuObject m = null;

        hov.pushMatrix();
        hov.translate(x, y);
        if(isMouseOver()){
            for(MenuObject mo : children){
                m = mo.getMouseOver();
                if(m != null){
                    break;
                }
            }

            if(m == null){
                m = this;
            }
        }
        hov.popMatrix();
        return m;
    }

    boolean isMouseOver() {
        return hov.mouseX == PApplet.constrain(hov.mouseX, hov.screenX(0, 0), hov.screenX(w, 0)) &&
                hov.mouseY == PApplet.constrain(hov.mouseY, hov.screenY(0, 0), hov.screenY(0, h));
    }

    MenuObject getClicked() {
        MenuObject m = null;

        if (isMouseOver()) {

            // Check all child objects first
            for (MenuObject mo : children) {
                m = mo.getClicked();

                if (m != null) {
                    break;
                }
            }

            //If no child objects were hit, check if it is on the current object
            if (m == null && isMouseOver()) {
                m = this;
            }
        }

        return m;
    }

    void onClickLeft(){

    }

    void onClickRight(){

    }

    void onHoldLeft(){

    }

    void onHoldRight(){

    }

    public void move(int tx, int ty) {
        x = tx;
        y = ty;
        nx = x + w + profile.MARGIN;
        ny = y + h + profile.MARGIN;
    }

    public void resize(int tw, int th) {
        if (tw != 0) {
            w = tw;
            nx = x + w + profile.MARGIN;
        }
        if (th != 0) {
            h = th;
            ny = y + h + profile.MARGIN;
        }
    }

    public void resizeToFit() {
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < children.size(); i++) {
            MenuObject m = children.get(i);
            maxX = PApplet.max(maxX, m.nx);
            maxY = PApplet.max(maxY, m.ny);
        }
        resize(maxX, maxY);
    }

    public void reset() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).reset();
        }
    }

    public void addChild(MenuObject o) {
        children.add(o);
    }

    public void removeChild(MenuObject o) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) == o) {
                children.remove(o);
                return;
            }
        }
    }

    public void clearChildren() {
        children.clear();
    }

    void updateScreenCoordinates() {
        sx = PApplet.round(hov.screenX(0, 0));
        sy = PApplet.round(hov.screenY(0, 0));
    }

    void display() {
        display(hov.g);
    }

    void display(PGraphics pg) {
        pg.pushMatrix();
        pg.translate(x, y);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(pg);
        }
        pg.popMatrix();
    }
}
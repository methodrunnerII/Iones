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
    MenuObject parent;

    int x;
    int y;
    int w;
    int h;
    int nx;  //x coordinate of following object if placed next to this one
    int ny;  //y coordinate of following object if placed under this one
    int sx;  //Screen coordinates of the object
    int sy;

    Profile profile;
    int cfill;
    int cstroke;
    int ctext;

    public MenuObject(int tx, int ty, int tw, int th) {
        profile = DefaultProfile.menu;
        children = new ArrayList<MenuObject>();

        x = tx;
        y = ty;
        w = tw;
        h = th;
        nx = x + w + profile.MARGIN;
        ny = y + h + profile.MARGIN;
    }

    void debugStuff(){
        hov.pushMatrix();
        hov.translate(x, y);
            PApplet.println(this + ": " + x + " " + y + " " + w + " " + h);
            PApplet.println("screen: " + hov.screenX(x, 0) + " " + hov.screenY(0, y) + " " +
                                         hov.screenX(x + w, 0) + " " + hov.screenY(0, y + h));
            PApplet.println();

            for(MenuObject m : children){
                m.debugStuff();
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

    void clickEval(){
        for(int i = children.size()-1; i>= 0; i--){
            children.get(i).clickEval();
        }
    }

    void holdEval(){
        for(int i = children.size()-1; i>= 0; i--){
            children.get(i).holdEval();
        }
    }

    boolean isMouseOver() {
        return hov.mouseX == PApplet.constrain(hov.mouseX, hov.screenX(0, 0), hov.screenX(w, 0)) &&
                hov.mouseY == PApplet.constrain(hov.mouseY, hov.screenY(0, 0), hov.screenY(0, h));
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
        o.setParent(this);
    }

    public void removeChild(MenuObject o) {
        children.remove(o);
    }

    public void clearChildren() {
        children.clear();
    }

    void updateScreenCoordinates() {
        sx = PApplet.round(hov.screenX(0, 0));
        sy = PApplet.round(hov.screenY(0, 0));
    }

    public void setProfile(Profile p){
        profile = p;
    }
    public Profile getProfile(){return profile;}

    void selectColors(){
        cfill = profile.getColor(Profile.FILL);
        cstroke = profile.getColor(Profile.STROKE);
        ctext = profile.getColor(Profile.TEXT);

        if(Iones.getCurrent().mousedOver == this){
            cfill = profile.getColor(Profile.FILL, Profile.HOVER);
            cstroke = profile.getColor(Profile.STROKE, Profile.HOVER);
            ctext = profile.getColor(Profile.TEXT, Profile.HOVER);
        }
        if(Iones.getCurrent().clickedLeft == this){
            cfill = profile.getColor(Profile.FILL, Profile.CLICK);
            cstroke = profile.getColor(Profile.STROKE, Profile.CLICK);
            ctext = profile.getColor(Profile.TEXT, Profile.CLICK);
        }
    }

    void display() {
        display(hov.g);
    }

    void display(PGraphics pg) {
        selectColors();
        pg.pushMatrix();
        pg.translate(x, y);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(pg);
        }
        pg.popMatrix();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getNX() {
        return nx;
    }

    public int getNY() {
        return ny;
    }

    public MenuObject getParent() {
        return parent;
    }

    public void setParent(MenuObject parent) {
        this.parent = parent;
    }
}
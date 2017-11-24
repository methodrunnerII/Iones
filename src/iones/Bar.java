package iones;

import processing.core.PApplet;

public class Bar extends MenuObject {
    int length;

    public Bar(int x, int y, int w, int h){
        super(x, y, w, h);
        length = Iones.getProfile().MARGIN;
    }

    public void updateLength(){
        length = Iones.getProfile().MARGIN;
        for(MenuObject m : children){
            length += m.w + Iones.getProfile().MARGIN;
        }
        w = length;
    }

    public void addChild(MenuObject m){
        super.addChild(m);
        m.resize(m.w, h - 2*Iones.getProfile().MARGIN);
        m.move(length, Iones.getProfile().MARGIN);
        updateLength();
    }

    public void clearChildren(){
        super.clearChildren();
        updateLength();
    }

    public void debugStuff(){
        super.debugStuff();
        PApplet.println("children: " + children.size());
    }
}

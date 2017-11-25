package iones;

import processing.core.PApplet;

public class Bar extends MenuObject {
    int length;

    public Bar(int x, int y, int w, int h){
        super(x, y, w, h);
        length = profile.MARGIN;
    }

    public void updateLength(){
        length = profile.MARGIN;
        for(MenuObject m : children){
            length += m.w + profile.MARGIN;
        }
        w = length;
    }

    public void addChild(MenuObject m){
        super.addChild(m);
        m.resize(m.w, h - 2*profile.MARGIN);
        m.move(length, profile.MARGIN);
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

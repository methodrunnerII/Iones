package iones;

import processing.core.PGraphics;

public class MenuList extends MenuObject {
    int margin;
    public int l; //length of list, not of display window!

    public MenuList(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        margin = 2;
    }

    public void resize(int w, int h){
        super.resize(w, h);
        for(MenuObject m : children){
            m.resize(w, m.getH());
        }
    }

    public void addChild(MenuObject o) {
        o.move(margin, l);
        o.resize(w, o.h);
        super.addChild(o);
        fitToLength();
    }

    public void clearList() {
        children.clear();
        updateLength();
    }

    public void updateLength() {
        l = getLength();
    }

    public void fitToLength() {
        updateLength();
        resize(w, l);
    }

    public int getLength() {
        int output = 0;
        int n = children.size();
        for (int i = 0; i < n; i++) {
            output += children.get(i).h;
        }
        output += (n + 1) * margin;
        return output;
    }

    public void display(PGraphics p) {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(p);
        }
    }

    public void clear() {
        children.clear();
        updateLength();
    }
}
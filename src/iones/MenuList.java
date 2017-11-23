package iones;

import processing.core.PGraphics;

public class MenuList extends MenuObject {
    int margin;
    public int l;

    public MenuList(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        margin = 2;
        updateLength();
    }

    public void addObject(MenuObject o) {
        o.move(margin, l);
        o.resize(w, o.h);
        addChild(o);
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
        h = l;
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

    public void display() {
        display(hov.g);
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
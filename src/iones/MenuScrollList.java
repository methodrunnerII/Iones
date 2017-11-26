package iones;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.opengl.*;

public class MenuScrollList extends MenuList {
    LabelButton scrollButton;

    float offset;
    float offsetMax;

    PGraphics displaySpace;

    int scrollButtonSize;

    public MenuScrollList(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        scrollButtonSize = 8;
        scrollButton = new LabelButton("", w - scrollButtonSize, scrollButtonSize / 2, scrollButtonSize, scrollButtonSize);
        updateLength();
        displaySpace = hov.createGraphics(w, h, PApplet.P2D);
        ((PGraphicsOpenGL) displaySpace).textureSampling(2);
    }

    public void eval() {
        if (Iones.getCurrent().heldLeft == this) {
            //TODO: Fix this fucking mess
            scrollButton.x = w - scrollButtonSize;
            scrollButton.y += (hov.mouseY - hov.screenY(scrollButton.x + scrollButtonSize / 2, scrollButton.y + scrollButtonSize / 2));
            scrollButton.y = PApplet.constrain(scrollButton.y, scrollButton.h / 2, h - scrollButton.h / 2);
        }
        offset = PApplet.map(scrollButton.y, scrollButton.h / 2, h - scrollButton.h / 2, 0, PApplet.max(l - h, 0));
    }

    public void addChild(MenuObject o) {
        super.addChild(o);
        o.resize(w - scrollButton.w - 2 * profile.MARGIN, o.h);
    }

    public int getWidth() {
        return w - margin - scrollButton.w;
    }

    public void updateDisplaySpace() {
        displaySpace = hov.createGraphics(w, h);
    }

    public void move(int tx, int ty) {
        x = tx;
        y = ty;
        scrollButton.x = x + w - scrollButtonSize;
        scrollButton.y = y + scrollButtonSize / 2;
    }

    public void resize(int tw, int th) {
        w = tw;
        h = th;
        for (int i = 0; i < children.size(); i++) {
            children.get(i).resize(w - scrollButtonSize - margin, children.get(i).y);
        }
    }

    public void display() {
        display(hov.g);
    }

    public void display(PGraphics p) {
        p.pushMatrix();
        p.translate(x, y);
        scrollButton.display();

        displaySpace.beginDraw();
        displaySpace.colorMode(PApplet.HSB, 1);
        displaySpace.clear();
        displaySpace.pushMatrix();
        displaySpace.translate(0, -offset);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(displaySpace);
        }
        displaySpace.popMatrix();
        displaySpace.endDraw();
        p.image(displaySpace, 0, 0);
        p.popMatrix();
    }
}
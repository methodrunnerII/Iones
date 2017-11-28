package iones;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.opengl.*;

public class MenuScrollList extends MenuList {
    PGraphics displaySpace;
    Slider scrollBar;

    public MenuScrollList(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        int sliderWidth = 8;
        scrollBar = new Slider(tw-sliderWidth, 0, sliderWidth, th, 0, 0, 1, Slider.VERTICAL);

        displaySpace = hov.createGraphics(w-sliderWidth, h, PApplet.P2D);
        ((PGraphicsOpenGL) displaySpace).textureSampling(2);
    }

    public MenuObject getMouseOver(){
        MenuObject m = null;

        hov.pushMatrix();
        hov.translate(x, y);
        if(isMouseOver()){
            hov.pushMatrix();
            hov.translate(0, -scrollBar.value);
            for(MenuObject mo : children){
                m = mo.getMouseOver();
                if(m != null){
                    break;
                }
            }
            hov.popMatrix();
            if(m == null){
                m = scrollBar.getMouseOver();
            }
            if(m == null){
                m = this;
            }
        }
        hov.popMatrix();
        return m;
    }

    public void holdEval(){
        super.holdEval();
        scrollBar.holdEval();
    }

    public void addChild(MenuObject o) {
        int th = getH();
        super.addChild(o);
        resize(getW(), th);
    }

    public void updateLength(){
        super.updateLength();
        scrollBar.setMax(PApplet.max(l-getH(), 0));
        PApplet.println("new max: " + scrollBar.max);
    }

    public void move(int tx, int ty) {
        super.move(tx, ty);
        scrollBar.move(getW()-scrollBar.getW(), 0);
    }

    public void resize(int tw, int th) {
        super.resize(tw, th);
        for(MenuObject m : children){
            m.resize(tw-scrollBar.getW(), m.getH());
        }
        scrollBar.move(tw-scrollBar.getW(), 0);
        updateLength();
        resizeDisplaySpace();
    }

    public void resizeDisplaySpace() {
        displaySpace = hov.createGraphics(w-scrollBar.getW(), h);
    }

    public void display(PGraphics p) {
        p.pushMatrix();
        p.translate(x, y);

        displaySpace.beginDraw();
        displaySpace.colorMode(PApplet.HSB, 1);
        displaySpace.clear();
        displaySpace.pushMatrix();
        displaySpace.translate(0, -scrollBar.value);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(displaySpace);
        }
        displaySpace.popMatrix();
        displaySpace.endDraw();
        p.image(displaySpace, 0, 0);
        scrollBar.display(p);
        p.popMatrix();
    }

    public void display(){
        display(hov.g);
    }
}
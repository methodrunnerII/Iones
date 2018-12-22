package iones;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Viewport extends MenuObject {
    PGraphics pg;
    PVector offset;

    public Viewport(int x, int y, int w, int h){
        super(x, y, w, h);
        pg = Iones.getPApplet().createGraphics(Iones.getPApplet().max(w, 1), Iones.getPApplet().max(h, 1));
    }

    void holdEval(){
        super.holdEval();
        PApplet a = Iones.getPApplet();
        MenuObject m = Iones.getCurrent().getHeldRight();
        if(m != null && m.isChildOf(this)){
            offset.add(a.mouseX-a.pmouseX, a.mouseY-a.pmouseY);
        }
    }

    void display(PGraphics p){
        selectColors();
        p.pushMatrix();
        p.translate(x, y);
        pg.beginDraw();
        for(int i = 0; i < children.size(); i++){
            children.get(i).display(pg);
        }
        pg.endDraw();
        p.image(pg, 0, 0);
        p.popMatrix();
    }

    public void resize(int w, int h){
        super.resize(w, h);
        pg.resize(w, h);
    }
}

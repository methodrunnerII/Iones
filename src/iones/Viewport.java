package iones;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Viewport extends MenuObject {
    PGraphics pg;
    PVector offset;

    public Viewport(int x, int y, int w, int h){
        super(x, y, w, h);
        initPG(w, h);
        offset = new PVector(0, 0);
    }

    void initPG(int w, int h){
        pg = Iones.getPApplet().createGraphics(Iones.getPApplet().max(w, 1), Iones.getPApplet().max(h, 1));
    }

    void holdEval(){
        super.holdEval();
        PApplet a = Iones.getPApplet();
        MenuObject m = Iones.getCurrent().getHeldRight();

        if(m != null){
            if(m == this || m.isChildOf(this)){
                offset.add((a.mouseX-a.pmouseX)/Iones.scale, (a.mouseY-a.pmouseY)/Iones.scale);
            }
        }
    }

    void display(PGraphics p){
        selectColors();
        p.pushMatrix();
        p.translate(x, y);
        pg.beginDraw();
        pg.clear();
        pg.pushMatrix();
        pg.translate(offset.x, offset.y);
        for(int i = 0; i < children.size(); i++){
            children.get(i).display(pg);
        }
        pg.popMatrix();
        pg.endDraw();
        p.image(pg, 0, 0);
        p.popMatrix();
    }

    public void resize(int w, int h){
        super.resize(w, h);
        initPG(w, h);
    }
}

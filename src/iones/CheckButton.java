package iones;

import processing.core.PApplet;
import processing.core.PGraphics;

public class CheckButton extends Button {
    boolean checked;

    public CheckButton(int x, int y, int w, int h) {
        super(x, y, w, h);
        checked = false;
    }

    void onClickLeft() {
        checked = !checked;
    }

    public void display(PGraphics pg) {
        super.display(pg);
        pg.pushMatrix();
        pg.translate(x+w/4, y+h/4);
        if(checked){
            pg.fill(cstroke);
            pg.rect(0, 0, w/2, h/2);
        }
        pg.popMatrix();
    }

    public boolean isChecked(){
        return checked;
    }
}
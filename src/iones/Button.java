package iones;

import processing.core.PGraphics;
import processing.data.IntDict;

public class Button extends MenuObject {

  public Button(int tx, int ty, int tw, int th) {
    super(tx, ty, tw, th);
  }

  IntDict selectColors(){
    boolean mousedOver = Iones.getCurrent().getMousedOver() == this;
    boolean clicked =  Iones.getCurrent().getHeldLeft() == this || Iones.getCurrent().getHeldRight() == this;
    return Iones.getProfile().selectColors(mousedOver, clicked, Iones.getProfile().BUTTON);
  }

  void display(PGraphics pg){
    IntDict c = selectColors();
    pg.pushMatrix();
    pg.translate(x, y);
    pg.fill(c.get("fill"));
    pg.stroke(c.get("stroke"));
    pg.rect(0, 0, w, h);
    pg.popMatrix();
  }
}
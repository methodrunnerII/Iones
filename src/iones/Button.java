package iones;

import processing.core.PGraphics;
import processing.data.IntDict;

public class Button extends MenuObject {

  public Button(int tx, int ty, int tw, int th) {
    super(tx, ty, tw, th);
  }

  void selectColors(){
    boolean mousedOver = Iones.getCurrent().getMousedOver() == this;
    boolean clicked =  Iones.getCurrent().getHeldLeft() == this || Iones.getCurrent().getHeldRight() == this;
    Iones.getProfile().getButtonColors(colors, mousedOver, clicked);
  }

  void display(PGraphics pg){
    selectColors();
    pg.pushMatrix();
    pg.translate(x, y);
    pg.fill(colors.get("fill"));
    pg.stroke(colors.get("stroke"));
    pg.rect(0, 0, w, h);
    pg.popMatrix();
  }
}
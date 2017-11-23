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
    menuObjects = new ArrayList<MenuObject>();
    scrollButtonSize = 8;
    scrollButton = new LabelButton("", w - scrollButtonSize, scrollButtonSize/2, scrollButtonSize, scrollButtonSize);
    margin = 2;
    updateLength();
    displaySpace = hov.createGraphics(w, h, PApplet.P2D);
    ((PGraphicsOpenGL) displaySpace).textureSampling(2);
  }

  public void eval() {
    hov.pushMatrix();
    hov.translate(x, y);
    evalScroll();
    detectMouseOver();
    evalObjects();
    hov.popMatrix();
  }
  
  public void evalAlways(){
    reset();
    hov.pushMatrix();
    hov.translate(x, y);
    for(int i = 0; i < menuObjects.size(); i++){
      menuObjects.get(i).evalAlways();
    }
    hov.popMatrix();
  }

  public void evalScroll() {
    scrollButton.eval();
    if (scrollButton.held) {
      scrollButton.x = w - scrollButtonSize;
      scrollButton.y += (hov.mouseY - hov.screenY(scrollButton.x + scrollButtonSize/2, scrollButton.y + scrollButtonSize/2))/Iones.getScale();
      scrollButton.y = PApplet.constrain(scrollButton.y, scrollButton.h/2, h - scrollButton.h/2);
    }
    offset = PApplet.map(scrollButton.y, scrollButton.h/2, h - scrollButton.h/2, 0, PApplet.max(l-h, 0));
  }

  public void evalObjects() {
    hov.pushMatrix();
    hov.translate(0, -offset);
    if (mouseOver) {
      for (int i = 0; i < menuObjects.size(); i++) {
        menuObjects.get(i).eval();
      }
    }
    hov.popMatrix();
  }

  public void addObject(MenuObject o) {
    super.addObject(o);
    o.resize(o.w - scrollButton.w - 2*margin, o.h);
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
    scrollButton.x = x + w-scrollButtonSize;
    scrollButton.y = y + scrollButtonSize/2;
  }
  
  public void resize(int tw, int th){
    w = tw;
    h = th;
    for(int i = 0; i < menuObjects.size(); i++){
      menuObjects.get(i).resize(w-scrollButtonSize-margin, menuObjects.get(i).y);
    }
  }
  
  public void display(){
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
    for (int i = 0; i < menuObjects.size(); i++) {
      menuObjects.get(i).display(displaySpace);
    }
    displaySpace.popMatrix();
    displaySpace.endDraw();
    p.image(displaySpace, 0, 0);
    p.popMatrix();
  }
}
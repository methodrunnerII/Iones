import java.util.ArrayList;

import processing.core.PGraphics;

public class MenuList extends MenuObject {
  public ArrayList<MenuObject> menuObjects;

  int margin;
  public int l;

  public MenuList(int tx, int ty, int tw, int th) {
    super(tx, ty, tw, th);
    menuObjects = new ArrayList<MenuObject>();
    margin = 2;
    updateLength();
  }

  public void eval() {
    hov.pushMatrix();
    hov.translate(x, y);
    if (mouseOver) {
      for (int i = 0; i < menuObjects.size(); i++) {
        menuObjects.get(i).eval();
      }
    }
    hov.popMatrix();
  }
  
  public void evalAlways(){
    hov.pushMatrix();
    hov.translate(x, y);
    for(int i = menuObjects.size() - 1; i >= 0; i--){
      menuObjects.get(i).evalAlways();
    }
    hov.popMatrix();
  }

  public void addObject(MenuObject o) {
    o.move(margin, l);
    o.resize(w, o.h);
    menuObjects.add(o);
    fitToLength();
  }
  
  public void clearList(){
    menuObjects.clear();
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
    int n = menuObjects.size();
    for (int i = 0; i < n; i++) {
      output += menuObjects.get(i).h;
    }
    output += (n+1)*margin;
    return output;
  }

  public void display() {
    display(hov.g);
  }
  
  public void display(PGraphics p) {
    for (int i = 0; i < menuObjects.size(); i++) {
      menuObjects.get(i).display(p);
    }
  }

  public void reset() {
    for (int i = 0; i < menuObjects.size(); i++) {
      menuObjects.get(i).reset();
    }
  }
  
  public void clear(){
    menuObjects.clear();
    updateLength();
  }
}
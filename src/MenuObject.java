import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PGraphics;

public class MenuObject {
  static PApplet hov;

  public static void setPApplet(PApplet p){
    hov = p;
  }
	
  public ArrayList<MenuObject> children;

  public int x;
  public int y;
  public int w;
  public int h;
  int nx;  //x coordinate of following object if placed next to this one
  int ny;  //y coordinate of following object if placed under this one
  int sx;  //Screen coordinates of the object
  int sy;

  Profile profile;

  boolean mouseOver;
  
  public int margin;
  
  boolean outsideDelay;

  public MenuObject(int tx, int ty, int tw, int th) {
    x = tx;
    y = ty;
    w = tw;
    h = th;
    margin = 2;
    nx = x + w + margin;
    ny = y + h + margin;
    children = new ArrayList<MenuObject>();
    outsideDelay = false;
    
    profile = new Profile();
  }

  public void eval() {
    hov.pushMatrix();
    hov.translate(x, y);
    for (int i = 0; i < children.size(); i++) {
      children.get(i).eval();
    }
    hov.popMatrix();
  }
  
  public void evalAlways(){
    hov.pushMatrix();
    hov.translate(x, y);
    updateScreenCoordinates();
    for (int i = 0; i < children.size(); i++) {
      children.get(i).evalAlways();
    }
    hov.popMatrix();
  }

  public void display() {
    display(hov.g);
  }

  public void display(PGraphics pg) {
    pg.pushMatrix();
    pg.translate(x, y);
    for (int i = 0; i < children.size(); i++) {
      children.get(i).display(pg);
    }
    pg.popMatrix();
  }

  boolean isMouseOver(){
    return  hov.mouseX == PApplet.constrain(hov.mouseX, hov.screenX(0, 0), hov.screenX(w, 0)) &&
            hov.mouseY == PApplet.constrain(hov.mouseY, hov.screenY(0, 0), hov.screenY(0, h));
  }

  MenuObject getClicked(int type) {
    MenuObject m = null;

    if (isMouseOver()) {

      // Check all child objects first
      for (MenuObject mo : children) {
        m = mo.getClicked(type);

        if(m != null){
          break;
        }
      }

      //If no child objects were hit, check if it is on the current object
      if(m == null && isMouseOver()){
        m = this;
      }
    }

    return m;
  }

  public void move(int tx, int ty) {
    x = tx;
    y = ty;
    nx = x + w + margin;
    ny = y + h + margin;
  }

  public void resize(int tw, int th) {
    if(tw != 0){
      w = tw;
      nx = x + w + margin;
    }
    if(th != 0){
      h = th;
      ny = y + h + margin;
    }
  }
  
  public void resizeToFit(){
    int maxX = 0;
    int maxY = 0;
    for(int i = 0; i < children.size(); i++){
      MenuObject m = children.get(i);
      maxX = PApplet.max(maxX, m.nx);
      maxY = PApplet.max(maxY, m.ny);
    }
    resize(maxX, maxY);
  }

  public void reset() {
    for (int i = 0; i < children.size(); i++) {
      children.get(i).reset();
    }
  }
  
  public void addObject(MenuObject o){
    children.add(o);
  }
  
  public void removeObject(MenuObject o){
    for(int i = 0; i < children.size(); i++){
      if(children.get(i) == o){
        children.remove(o);
        return;
      }
    }
  }
  
  public void clear(){
    children.clear();
  }
  
  public void updateScreenCoordinates(){
    sx = PApplet.round(hov.screenX(0, 0));
    sy = PApplet.round(hov.screenY(0, 0));
  }
}
package iones;

import processing.core.PApplet;
import processing.core.PGraphics;

class MenuSwitch extends Menu{
  int index;
  MenuSwitch(int tx, int ty, int th, int tw){
    super(tx, ty, tw, th);
    index = 0;
  }
  
  public void eval(){
    hov.pushMatrix();
    hov.translate(x, y);
    index = PApplet.max(index, 0);
    index = PApplet.min(index, children.size()-1);
    children.get(index).eval();
    hov.popMatrix();
  }
  
  public void addMenu(Menu m){
    children.add(m);
  }
  
  public void setMenu(int n){
    index = n;
  }
  
  public void display(){
  	display(hov.g);
  }
  
  public void display(PGraphics p) {
    p.pushMatrix();
    p.translate(x, y);
    if (background != null) {
    	p.image(background, 0, 0);
    } else {
    	p.stroke(profile.MENUSTROKE);
    	p.fill(profile.MENUFILL);
      p.rectMode(PApplet.CORNER);
      p.rect(0, 0, w, h);
    }
    children.get(index).display();
    p.popMatrix();
  }
}
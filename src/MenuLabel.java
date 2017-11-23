package iones;

import hovinput.Mouse;
import hovtext.Text;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

class MenuLabel extends MenuObject {
  String text;
  PImage image;

  MenuLabel(String tt, PImage ti, int tx, int ty) {
    super(tx, ty, ti.width, ti.height);
    text = tt;
    image = ti;
  }
  
  public void setImage(PImage i){
    image = i;
  }
  
  public void display(){
  	display(hov.g);
  }

  public void display(PGraphics p) {
    p.pushMatrix();
    p.translate(x, y);
    if(image == null){
    	p.stroke(1);
    	p.fill(0.5f);
      if (mouseOver) {
      	p.fill(0.15f, 1, 1);
        if (Mouse.held) {
        	p.fill(1, 1, 1);
        }
      }
      p.rectMode(PApplet.CORNER);
      p.rect(0, 0, w, h);
    } else {
    	p.image(image, x, y);
    }
    Text.displayText(text, margin, margin, p);
    p.popMatrix();
  }
}
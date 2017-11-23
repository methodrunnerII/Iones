package iones;

import processing.core.PGraphics;
import processing.core.PImage;

class ImageButton extends LabelButton{
  PImage imageRegular;
  PImage imageMouseOver;
  PImage imageHeld;
  
  ImageButton(String tn, PImage timg, int tx, int ty){
    super(tn, tx, ty, 0, 0);
    if(timg != null){
      resize(timg.width, timg.height);
    }
    imageRegular = timg;
    imageMouseOver = timg;
    imageHeld = timg;
  }
  
  public void setImage(PImage img){
    imageRegular = img;
    resize(img.width, img.height);
  }
  
  public void display(){
  	display(hov.g);
  }

  public void display(PGraphics pg) {
    pg.pushMatrix();
    pg.translate(x, y);
//    if (mouseOver) {
//      if (iones.Mouse.held) {
//        pg.image(imageHeld, 0, 0);
//      } else {
//        pg.image(imageMouseOver, 0, 0);
//      }
//    } else {
//      pg.image(imageRegular, 0, 0);
//    }
//    pg.fill(1);
//    pg.noStroke();
//    //Text.displayText(name, margin, h-margin, pg);
    pg.popMatrix();
  }
}
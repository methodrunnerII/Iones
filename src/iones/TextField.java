package iones;

import processing.core.PApplet;
import processing.core.PGraphics;

public class TextField extends LabelButton{  
  String fieldName;
  String value;
  String tempValue;
  
  int indicator;
  int speed;
  int speedTimer;
  
  boolean active;
  boolean canType;
  
  char lastPressed;
  
  public TextField(String tn, int tx, int ty, int tw, int th){
    super("", tx, ty, tw, th);
    
    fieldName = tn + " ";
    value = "";
    tempValue = "";
    
    active = false;
    canType = true;
  }
  
  public void set(String s){
    value = s;
    tempValue = s;
  }

  @Override
  void onClickLeft() {
    Iones.getCurrent().setTextfield(this);
  }

  public void evalAlways(){
    if(active && canType && Keyboard.pressed){
      if(hov.key == PApplet.BACKSPACE){
        if(indicator != 0){
          tempValue = tempValue.substring(0, indicator-1) + tempValue.substring(indicator, tempValue.length());
          indicator = PApplet.max(0, indicator-1);
        }
      } else if(hov.key == PApplet.ENTER){
        active = false;
        value = tempValue;
      } else if(hov.keyCode == PApplet.SHIFT){
        //Do nothing to allow for capitalized characters
      } else if(hov.keyCode == PApplet.LEFT){
        indicator = PApplet.max(0, indicator-1);
      } else if(hov.keyCode == PApplet.RIGHT){
        indicator = PApplet.min(tempValue.length(), indicator+1);
      } else {
        tempValue = tempValue.substring(0, indicator) + hov.key + tempValue.substring(indicator, tempValue.length());
        indicator++;
        lastPressed = hov.key;
      }
      canType = false;
      Keyboard.pressed = false;
    }
    if(hov.key != lastPressed || !Keyboard.pressed || hov.keyCode == PApplet.SHIFT){
      canType = true;
    }
    name = fieldName + tempValue;
    hov.popMatrix();
  }
  
  
  
  public void display(PGraphics p){
    super.display(p);
    if(active){
      //int lx = Text.getTextLength(fieldName + tempValue.substring(0, indicator));
      int lx = hov.ceil(hov.textWidth(fieldName + tempValue.substring(0, indicator)));
      p.pushMatrix();
      p.translate(x, y);
      p.line(lx, 2, lx, 10);
      p.popMatrix();
    }
  }
}
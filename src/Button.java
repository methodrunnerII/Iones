import processing.core.PApplet;

class Button extends MenuObject {
  float value;

  public boolean clicked;
  public boolean held;
  public boolean leftClicked;
  public boolean rightClicked;
  public boolean leftHeld;
  public boolean rightHeld;

  Button(int tx, int ty, int tw, int th) {
    super(tx, ty, tw, th);
  }

  public void eval() {
    hov.pushMatrix();
    hov.translate(x, y);
    detectMouseOver();
    hov.popMatrix();
    detectClick();
  }
  
  public void evalAlways(){
  	hov.pushMatrix();
  	hov.translate(x, y);    
    updateScreenCoordinates();
    reset();
    hov.popMatrix();
  }

  public void detectClick() {
    if (mouseOver && Mouse.clicked) {
      clicked = true;
      Menu.menuHit = true;
      leftClicked = Mouse.leftClicked;
      rightClicked = Mouse.rightClicked;
    }
    if (mouseOver && Mouse.held) {
      held = true;
      if(hov.mouseButton == PApplet.LEFT){
        leftHeld = true;
      }
      if(hov.mouseButton == PApplet.RIGHT){
        rightHeld = true;
      }
    } else {
      held = false;
      leftHeld = false;
      rightHeld = false;
    }
  }

  public void reset() {
    clicked = false;
    leftClicked = false;
    rightClicked = false;
    held = false;
    mouseOver = false;
  }
}
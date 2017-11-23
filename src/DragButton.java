import processing.core.PApplet;

class DragButton extends Button {
  DragButton(int tx, int ty, int tw, int th){
    super(tx, ty, tw, th);
  }
  
  public void detectClick(){
    if (mouseOver && Mouse.clicked) {
      Menu.menuHit = true;
      clicked = true;
      Menu.menuHit = true;
      held = true;
      leftClicked = Mouse.leftClicked;
      rightClicked = Mouse.rightClicked;
    }
    if (held && Mouse.held) {
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
    mouseOver = false;
  }
}
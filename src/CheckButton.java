import processing.core.PApplet;

class CheckButton extends MenuObject{
  String name;
  boolean checked;
  LabelButton button;
  
  CheckButton(String name, int x, int y, int w, int h){
    super(x, y, w, h);
    this.name = name;
    checked = false;
    button = new LabelButton("", PApplet.ceil(4 + hov.textWidth(name)), 2, w, h);
    
    if (w == 0) {
      resize(PApplet.ceil(hov.textWidth(name)) + button.w + 4, h);
    }
    if (h == 0) {
      resize(w, PApplet.ceil(hov.textAscent()) + 4);
    }
  }
    
  public void eval(){
    hov.pushMatrix();
    hov.translate(x, y);
    button.eval();
    if(button.leftClicked){
      checked = !checked;
      if(checked){
        button.name = "X";
      } else {
        button.name = "";
      }
    }
    hov.popMatrix();
  }
  
  public void display(){
    button.display();
    hov.fill(1);
    hov.text(name, margin, h-margin);
  }
}
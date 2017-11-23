import processing.core.PApplet;
import processing.core.PGraphics;

public class LabelButton extends Button {
  public String name;
  
  int strokeHover;
  int fillHover;
  int colorClick;
  int colorText;

  public LabelButton(String tn, int tx, int ty, int tw, int th) {
    super(tx, ty, tw, th);
    name = tn;
    if (w == 0) {
      resize(PApplet.ceil(hov.textWidth(name)) + 4, h);
    }
    if (h == 0) {
      resize(w, PApplet.ceil(hov.textAscent()) + 4);
    }
  }

  public void display() {
  	display(hov.g);
  }

  public void display(PGraphics pg) {
    pg.stroke(profile.BUTTONSTROKE);
    pg.fill(profile.BUTTONFILL);
    if (mouseOver) {
      pg.fill(fillHover);
      pg.stroke(strokeHover);
      if (Mouse.held) {
        pg.fill(colorClick);
      }
    }
    pg.fill(colorText);
    pg.rectMode(PApplet.CORNER);
    pg.rect(x, y, w, h);
    hov.text(name, x+2, y+2);
    //Text.displayText(name, x + 2, y + 2, pg);
  }
}
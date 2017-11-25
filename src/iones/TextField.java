package iones;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class TextField extends Button {
    static PApplet p;

    public static void setPApplet(PApplet p) {
        TextField.p = p;
    }

    String fieldName;
    String value;
    String tempValue;

    int indicator;
    int speed;
    int speedTimer;

    boolean active;
    boolean canType;

    public TextField(String tn, int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);

        fieldName = tn + ": ";
        value = "";
    }

    public void set(String s) {
        value = s;
    }

    void onKeystroke(int k) {
        if (k == PApplet.BACKSPACE) {
            if (indicator != 0) {
                value = value.substring(0, indicator - 1) + value.substring(indicator, value.length());
                indicator = PApplet.max(0, indicator - 1);
            }
        } else if (k == PApplet.ENTER) {
            Iones.getCurrent().setTextfield(null);

        } else if (k == PApplet.SHIFT || k == PApplet.CONTROL || k == PApplet.ALT || k == 19 /*alt gr*/) {
            //Do nothing to allow for capitalized characters

        } else if (k == PApplet.LEFT) {
            indicator = PApplet.max(0, indicator - 1);

        } else if (k == PApplet.RIGHT) {
            indicator = PApplet.min(value.length(), indicator + 1);

        } else {
            value = value.substring(0, indicator) + hov.key + value.substring(indicator, value.length());
            indicator++;
        }
    }

    public void display(PGraphics p) {
        super.display(p);
        p.pushMatrix();
        p.translate(x, y);
        p.fill(ctext);
        p.textAlign(PConstants.LEFT, PConstants.CENTER);
        p.text(fieldName + value, profile.MARGIN, profile.MARGIN);

        if (Iones.getCurrent().getTextfield() == this) {
            p.stroke(ctext);
            int lx = hov.ceil(hov.textWidth(fieldName + value.substring(0, indicator)));
            p.line(lx, 2, lx, 10);
        }

        p.popMatrix();
    }
}
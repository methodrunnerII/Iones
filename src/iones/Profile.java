package iones;

import processing.core.PApplet;
import processing.data.IntDict;

public class Profile {
    static PApplet hov;

    public static void setPApplet(PApplet p) {
        hov = p;
    }

    final static int MENU = 0;
    final static int BUTTON = 1;

    public int MENUSTROKE;
    public int MENUFILL;

    public int BUTTONFILL;
    public int BUTTONSTROKE;
    public int BUTTONTEXT;
    public int BUTTONHOVER;
    public int BUTTONCLICK;

    public int MENUBLUE;
    public int CLEAR;

    public int MARGIN;

    Profile() {
        MENUSTROKE = hov.color(0, 0, 0.4f);
        MENUFILL = hov.color(0, 0, 0.2f);

        BUTTONFILL = hov.color(0, 0, 0.3f);
        BUTTONSTROKE = hov.color(0, 0, 0.15f);
        BUTTONTEXT = hov.color(0, 0, 0.8f);
        BUTTONHOVER = hov.color(0, 0, 0.5f);
        BUTTONCLICK = hov.color(0.15f, 1, 1);

        MENUBLUE = hov.color(0.6f, 0.4f, 0.3f);

        CLEAR = hov.color(0, 0, 0, 0.01f);

        MARGIN = 2;
    }

    IntDict selectColors(boolean mouseOver, boolean clicked, int type) {
        IntDict c = new IntDict(3);

        if (type == MENU) {
            c.set("fill", MENUFILL);
            c.set("stroke", MENUSTROKE);
            c.set("text", BUTTONTEXT);

        } else if (type == BUTTON) {
            c.set("text", BUTTONTEXT);
            c.set("stroke", BUTTONSTROKE);
            if (mouseOver) {
                if (clicked) {
                    c.set("fill", BUTTONCLICK);
                } else {
                    c.set("fill", BUTTONHOVER);
                }
            } else {
                c.set("fill", BUTTONFILL);
            }
        }

        return c;
    }
}

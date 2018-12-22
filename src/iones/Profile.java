package iones;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.data.IntDict;

public class Profile {
    static PApplet hov;

    public static void setPApplet(PApplet p) {
        hov = p;
    }

    public static final int STROKE = 0;
    public static final int FILL = 1;
    public static final int TEXT = 2;

    public static final int REGULAR = 0;
    public static final int HOVER = 1;
    public static final int CLICK = 2;

    public static final int ERROR = 0xff00ffff;
    public static final int CLEAR = 0xffffff00;

    public int MARGIN;

    int[][] colors;

    int COLORMODE;
    int COLORMODELIMIT;

    Profile(int stroke, int fill, int text) {
        colors = new int[3][3];
        for(int i = 0; i < 3; i++) {
            colors[STROKE][i] = stroke;
            colors[FILL][i] = fill;
            colors[TEXT][i] = text;
        }

        MARGIN = 2;
    }

    public int getColor(int part, int state){
        if(part < 0 || part >= colors.length || state < 0 || state >= colors[0].length){
            return ERROR;
        }
        return colors[part][state];
    }

    public int getColor(int part){
        return getColor(part, 0);
    }

    public void setColor(int part, int state, int c){
        if(part < 0 || part >= colors.length || state < 0 || state >= colors[0].length){
            PApplet.println("Error: couldn't set profile color, out of bounds");
            return;
        }

        colors[part][state] = c;
    }
}

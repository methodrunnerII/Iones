package iones;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Iones {
    static PApplet hov;

    static float scale;

    static MenuBuffer buffer;
    static Current current;

    static Profile profile;

    public static void initialize(PApplet p, float scale){
        p.colorMode(PConstants.HSB, 1);
        Iones.hov = p;
        MenuObject.setPApplet(p);
        Profile.setPApplet(p);
        setScale(scale);

        buffer = new MenuBuffer();
        current = new Current();
        profile = new Profile();
    }

    public static void getMouseOver(){
        MenuObject m = buffer.getMouseOver();
        if(m != null){
            current.setMousedOver(m);
        }
    }

    public static void mousePressed(int type){
        current.setClickedLeft(null);
        current.setClickedRight(null);
        current.setTextfield(null);

        MenuObject m = buffer.getClicked();

        if(m != null){
            if(type == PConstants.LEFT){
                current.setClickedLeft(m);
                if(m instanceof TextField){
                    current.setTextfield((TextField) m);
                }
                m.onClickLeft();
            } else if(type == PConstants.RIGHT){
                current.setClickedRight(m);
                m.onClickRight();
            }
        }
        buffer.removeVolatile();
    }

    public static void mouseReleased(int type){
        current.setHeldLeft(null);
        current.setHeldRight(null);
    }

    public static void keyPressed(int k){
        if(current.getTextfield() != null){
            current.getTextfield().onKeystroke(k);
        }
    }

    public static void wrapup(){
        for(Menu m : buffer.menus){
            if(m.done){
                buffer.remove(m);
            }
        }

        current.setMousedOver(null);
        current.setClickedLeft(null);
        current.setClickedRight(null);
    }

    public static MenuBuffer getBuffer(){
        return buffer;
    }

    public static float getScale() {
        return scale;
    }

    public static void setScale(float scale) {
        Iones.scale = scale;
    }

    public static Current getCurrent(){
        return current;
    }

    public static Profile getProfile(){
        return profile;
    }

    public static void display(PGraphics pg){
        for(Menu m : buffer.menus){
            m.display(pg);
        }
    }

    public static void display(){
        for(Menu m : buffer.menus){
            m.display();
        }
    }

    public static void displayText(String s, int x, int y, PGraphics pg){
        pg.text(s, x, y);
    }
}


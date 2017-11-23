import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Menu extends MenuObject {
    public static ArrayList<Menu> menuBuffer = new ArrayList<Menu>();
    static Boolean menuHit;

    public String name;  //Name of the menu displayed at the top

    public static boolean useRegularText;
    public static boolean fullScreen;
    boolean isVolatile;  //when true, menuObject sets done = true when clicked outside
    public boolean done;

    PImage background;

    static {
        useRegularText = false;
    }

    public Menu(int tx, int ty, int tw, int th) {
        super(tx, ty, tw, th);
        done = false;
        isVolatile = false;
    }

    public void addObject(MenuObject m) {
        children.add(m);
    }

    public void setBackground(PImage img) {
        background = img;
    }

    public void reset() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).reset();
        }
    }

    public void display() {
        display(hov.g);
    }

    public void display(PGraphics pg) {
        pg.pushMatrix();
        pg.translate(x, y);
        if (background != null) {
            pg.image(background, 0, 0);
        } else {
            pg.stroke(profile.MENUSTROKE);
            pg.fill(profile.MENUFILL);
            pg.rectMode(PApplet.CORNER);
            pg.rect(0, 0, w, h);
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(pg);
        }
        pg.popMatrix();
    }

    public static void evalMenus() {
        for (int i = 0; i < menuBuffer.size(); i++) {
            if (menuBuffer.get(i).done) {
                popBuffer(i);
            }
        }
        for (int i = 0; i < menuBuffer.size(); i++) {
            menuBuffer.get(i).evalAlways();
        }
        for (int i = 0; i < menuBuffer.size(); i++) {
            if (menuBuffer.get(i).done) {
                popBuffer(i);
            }
        }
        if (menuBuffer.size() != 0) {
            Menu m = menuBuffer.get(menuBuffer.size() - 1);
            m.eval();
        }
    }

    public static void clearMenuBuffer() {
        for (int i = 0; i < menuBuffer.size(); i++) {
            menuBuffer.get(i).reset();
        }
        menuBuffer = new ArrayList<Menu>();
    }

    public static void pushBuffer(Menu m) {
        menuBuffer.add(m);
    }

    public static void popBuffer(int i) {
        menuBuffer.get(i).done = false;
        menuBuffer.get(i).reset();
        menuBuffer.remove(i);
    }

    public static void displayMenus() {
        for (int i = 0; i < menuBuffer.size(); i++) {
            menuBuffer.get(i).display();
        }
    }
}
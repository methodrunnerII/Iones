import processing.core.PApplet;

public class Iones {
    static float scale;
    static TextField currentTextfield;
    static MenuObject currentlyClickedLeft;
    static MenuObject currentlyHeldLeft;
    static MenuObject currentlyClickedRight;
    static MenuObject currentlyHeldRight;

    static MenuBuffer buffer;

    public static void initialize(PApplet p, float scale){
        buffer = new MenuBuffer();
        MenuObject.setPApplet(p);
        Mouse.setPApplet(p);
        Keyboard.setPApplet(p);
        Profile.setPApplet(p);
        setScale(scale);
    }

    public static void mousePressed(){

    }

    public static void mouseReleased(){

    }

    public static void wrapup(){
        currentlyClickedLeft = null;
        currentlyClickedRight = null;
    }

    public static float getScale() {
        return scale;
    }

    public static void setScale(float scale) {
        Iones.scale = scale;
    }

}

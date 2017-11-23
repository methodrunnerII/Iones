import processing.core.PApplet;

public class Iones {
    static float scale;

    public static void initialize(PApplet p, float scale){
        MenuObject.setPApplet(p);
        Mouse.setPApplet(p);
        Keyboard.setPApplet(p);
        Profile.setPApplet(p);
        setScale(scale);
    }

    public static float getScale() {
        return scale;
    }

    public static void setScale(float scale) {
        Iones.scale = scale;
    }
}

import processing.core.PApplet;

public class Mouse{
  static PApplet hov;
  public static boolean clicked;
  public static boolean leftClicked;    //If the mouse has just been clicked
  public static boolean rightClicked;
  public static boolean held;       //If the mouse is currently being held down
  static boolean released;   //If the mouse has just been released
  static int clickFrame;
  static int releaseFrame;
  
  public static void haveClicked(){
    clicked = false;
    released = false;
  }
  


  public static void setPApplet(PApplet p){ hov = p; }
}
import processing.core.PApplet;

public class Keyboard{
  static PApplet hov;

  public static boolean pressed;
  static boolean once;
  
  public static Key up;
  public static Key down;
  public static Key left;
  public static Key right;
  public static Key interact;
  public static Key charMenu;
  public static Key inventory;
  public static Key stab;
  public static Key hack;
  public static Key slash;
  public static Key block;
  
  public static Key debug;
  
  static{
    debug = new Key('0');
  }
  
  public static void evalEnd(){
    up.evalEnd();
    down.evalEnd();
    left.evalEnd();
    right.evalEnd();
    interact.evalEnd();
    charMenu.evalEnd();
    inventory.evalEnd();
    stab.evalEnd();
    hack.evalEnd();
    slash.evalEnd();
    block.evalEnd();
    debug.evalEnd();
  }
  
  public static void keyPressed(){
  	char key = hov.key;
    if(key == up.name){
      up.press();
    }
    
    if(key == down.name){
      down.press();
    }
    
    if(key == left.name){
      left.press();
    }
    
    if(key == right.name){
      right.press();
    }
    
    if(key == interact.name){
      interact.press();
    }
    
    if(key == charMenu.name){
      charMenu.press();
    }
    
    if(key == inventory.name){
      inventory.press();
    }
    
    if(key == stab.name){
      stab.press();
    }
    
    if(key == hack.name){
      hack.press();
    }
    
    if(key == slash.name){
      slash.press();
    }
    
    if(key == block.name){
      block.press();
    }
    
    if(key == debug.name){
      debug.press();
    }
  }

  public static void keyReleased(){
  	char key = hov.key;
    if(key == up.name){
      up.release();
    }
    
    if(key == down.name){
      down.release();
    }
    
    if(key == left.name){
      left.release();
    }
    
    if(key == right.name){
      right.release();
    }
    
    if(key == interact.name){
      interact.release();
    }
    
    if(key == charMenu.name){
      charMenu.release();
    }
    
    if(key == inventory.name){
      inventory.release();
    }
    
    if(key == stab.name){
      stab.release();
    }
    
    if(key == hack.name){
      hack.release();
    }
    
    if(key == slash.name){
      slash.release();
    }
    
    if(key == block.name){
      block.release();
    }
    
    if(key == debug.name){
      debug.release();
    }
  }

  public static void setPApplet(PApplet p){ hov = p; }
}

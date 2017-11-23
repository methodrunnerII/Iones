package iones;

public class Key{
  public char name;
  public boolean pressed;
  public boolean released;
  public boolean held;
  
  public Key(char tn){
    name = tn;
    pressed = false;
    released = false;
    held = false;
  }
  
  public void press(){
    if(!held){
      pressed = true;
    }
    held = true;
  }
  
  public void release(){
    released = true;
    held = false;
  }
  
  public void evalEnd(){
    pressed = false;
    released = false;
  }
}
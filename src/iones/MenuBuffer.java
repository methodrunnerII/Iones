package iones;

import java.util.ArrayList;

public class MenuBuffer {
    ArrayList<Menu> menus;

    MenuBuffer(){
        menus = new ArrayList();
    }

    MenuObject getMouseOver(){
        MenuObject m = null;
        for(int i = menus.size()-1; i >= 0; i--){

            m = menus.get(i).getMouseOver();
            if(m != null) {
                break;
            }
        }
        return m;
    }

    void clickEval(){
        for(int i = menus.size()-1; i >= 0; i--){
            menus.get(i).clickEval();
        }
    }

    public void debugStuff(){
        for(Menu m : menus){
            m.debugStuff();
        }
    }

    public void add(Menu m){
        menus.add(m);
    }

    public void insert(Menu m, int i){
        menus.add(i, m);
    }

    public void remove(Menu m){
        menus.remove(m);
    }

    public void clear(){
        menus.clear();
    }

    void removeVolatile(){
        for(int i = menus.size()-1; i >= 0; i--){
            Menu m = menus.get(i);
            if(m.isVolatile && !Iones.getCurrent().isCurrent(m)){
                remove(m);
            }
        }
    }
}

import java.util.ArrayList;

public class MenuBuffer {
    ArrayList<Menu> menus;

    MenuBuffer(){
        menus = new ArrayList();
    }

    void eval(){
        for(int i = menus.size()-1; i >= 0; i--){
            menus.get(i).eval();
        }
    }

    void getEvent(int type){
        for(int i = menus.size()-1; i >= 0; i--){
            if(menus.get(i).event(type)){
                return;
            }
        }
    }

    void add(Menu m){
        menus.add(m);
    }

    void insert(Menu m, int i){
        menus.add(i, m);
    }

    void
}

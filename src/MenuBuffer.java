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

    MenuObject getClicked(int type){
        MenuObject m = null;
        for(int i = menus.size()-1; i >= 0; i--){
            m = menus.get(i).getClicked(type);
            if(m != null) {
                break;
            }
        }
        return m;
    }

    void add(Menu m){
        menus.add(m);
    }

    void insert(Menu m, int i){
        menus.add(i, m);
    }
}

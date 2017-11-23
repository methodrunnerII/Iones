package iones;

import java.util.ArrayList;

public class TabbedMenu extends Menu{
    Menu currentMenu;
    ArrayList<Menu> menus;
    Bar tabs;

    public TabbedMenu(int x, int y, int w, int h){
        super(x, y, w, h);
        menus = new ArrayList<Menu>();
        tabs = new Bar(x, y, w, 20);
        currentMenu = null;
    }

    public void setMenu(Menu m){
        if(currentMenu != null){
            children.remove(currentMenu);
        }
        currentMenu = (Menu) m;
        addChild(currentMenu);
    }

    public void addMenu(Menu m){
        menus.add(m);
        if(menus.size() == 1){
            setMenu(m);
        }
        updateTabs();
    }

    public void updateTabs(){
        tabs.clearChildren();
        for(MenuObject m : children){
            tabs.addChild(new LabelButton(((Menu) m).name, 0, 0, 0, 0));
        }
    }

    public void onClickLeft(){
        super.onClickLeft();

        for(MenuObject m : tabs.children){
            if(Iones.getCurrent().clickedLeft == m){
                setMenu((Menu) m);
            }
        }
    }
}

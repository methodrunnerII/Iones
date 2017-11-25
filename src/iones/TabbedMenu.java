package iones;

import processing.core.PGraphics;

import java.util.ArrayList;

public class TabbedMenu extends Menu{
    Menu currentMenu;
    ArrayList<Menu> menus;
    Bar tabs;

    public TabbedMenu(int x, int y, int w, int h){
        super(x, y, w, h);
        menus = new ArrayList<Menu>();
        tabs = new Bar(x, y, w, 24);
        addChild(tabs);
        currentMenu = null;
    }

    public void setMenu(Menu m){
        if(currentMenu != null){
            children.remove(currentMenu);
        }
        currentMenu = m;
        addChild(currentMenu);
    }

    public Menu getCurrentMenu(){
        return currentMenu;
    }

    public void addMenu(Menu m){
        menus.add(m);
        m.move(m.x, tabs.ny);
        if(menus.size() == 1){
            setMenu(m);
        }
        updateTabs();
    }

    public void updateTabs(){
        tabs.clearChildren();
        for(Menu m : menus){
            tabs.addChild(new LabelButton(m.name, 0, 0, 0, 0));
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

    public void display(PGraphics pg) {
        selectColors();
        pg.pushMatrix();
        pg.translate(x, y);

        for (int i = 0; i < children.size(); i++) {
            children.get(i).display(pg);
        }
        pg.popMatrix();
    }
}

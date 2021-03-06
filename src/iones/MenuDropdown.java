package iones;

public class MenuDropdown extends LabelButton{
  MenuList list;
  Menu listMenu;
  
  public MenuDropdown(String name, int x, int y, int w, int h, MenuList tlist){
    super(name + " v ", x, y, w, h);
    list = tlist;
    list.move(list.margin, list.margin);
    listMenu = new Menu(x, ny, list.w+2*list.margin, list.h);
    listMenu.isVolatile = true;
    listMenu.addChild(list);
  }
  
  void onClickLeft(){
    Iones.buffer.add(listMenu);
  }
}
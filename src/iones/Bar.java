package iones;

public class Bar extends MenuObject {
    int length;

    public Bar(int x, int y, int w, int h){
        super(x, y, w, h);
        length = Iones.getProfile().MARGIN;
    }

    public void updateLength(){
        length = Iones.getProfile().MARGIN;
        for(MenuObject m : children){
            length += m.w + Iones.getProfile().MARGIN;
        }
    }

    public void addChild(MenuObject m){
        super.addChild(m);
        m.resize(m.w, m.h);
        m.move(length, Iones.getProfile().MARGIN);
        updateLength();
    }

    void display(){

    }
}

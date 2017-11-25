package iones;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.data.FloatList;

import static processing.core.PApplet.round;

public class Partition extends MenuObject {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    FloatList splitCoords;
    int direction;

    public Partition(){
        super(0, 0, 0, 0);
        resize(parent.getW(), parent.getH());
        splitCoords = new FloatList();
        direction = HORIZONTAL;
    }

    void updateChildren(){
        if(children.size() == 0){
            return;
        }
        if(children.size() == 1){
            MenuObject m = children.get(0);
            m.move(profile.MARGIN, profile.MARGIN);
            m.resize(m.getW()-2*profile.MARGIN, m.getH()-2*profile.MARGIN);
        } else {
            int[] x = new int[children.size()];
            int[] y = new int[children.size()];
            int[] w = new int[children.size()];
            int[] h = new int[children.size()];

            for(int i = 0; i < children.size()-1; i++){
                x[i] = children.get(i).getX();
                y[i] = children.get(i).getY();
                w[i] = children.get(i).getW();
                h[i] = children.get(i).getH();
            }

            if(direction == HORIZONTAL){
                x[0] = profile.MARGIN;
                for(int i = 0; i < children.size()-1; i++){
                    x[i+1] = round(splitCoords.get(i)*getW() + profile.MARGIN);
                    w[i] = x[i+1] - x[i] - profile.MARGIN;
                }
                w[w.length] = getW() - x[x.length] - profile.MARGIN;
            } else {
                y[0] = profile.MARGIN;
                for(int i = 0; i < children.size()-1; i++){
                    y[i+1] = round(splitCoords.get(i)*getH() + profile.MARGIN);
                    h[i] = y[i+1] - y[i] - profile.MARGIN;
                }
                h[h.length] = getH() - y[y.length] - profile.MARGIN;
            }

            for(int i = 0; i < children.size(); i++){
                children.get(i).move(x[i], y[i]);
                children.get(i).resize(w[i], h[i]);
            }
        }
    }

    public void splitEvenly(){
        if(direction == HORIZONTAL){
            for(int i = 0; i < splitCoords.size(); i++){
                splitCoords.set(i, getW()*(i+1)/(splitCoords.size()+1));
            }
        }

        updateChildren();
    }

    public void setSplit(int[] s){
        if(s.length != children.size()-1){
            PApplet.println("Error: could not set partition split, incorrect amount of elements.");
            return;
        }

        splitCoords.clear();
        for(int i : s){
            float c;
            if(direction == HORIZONTAL){
                c = ((float) i)/getW();
            } else {
                c = ((float) i)/getH();
            }
            splitCoords.append(i);
        }

        updateChildren();
    }

    public void setSplit(float[] s){
        splitCoords.clear();
        for(float i : s){
            splitCoords.append(i);
        }
        updateChildren();
    }

    public void setDirection(int d){
        direction = d;
        updateChildren();
    }

    public void display(PGraphics pg){
        for(MenuObject m : children){
            m.display(pg);
        }
    }
}

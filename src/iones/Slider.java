package iones;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Slider extends MenuObject {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    float min;
    float max;
    float value;
    float step;
    int direction;

    Button buttonLess;
    Button buttonMore;
    Button buttonScroll;

    public Slider(int x, int y, int w, int h, float min, float max, float step, int direction){
        super(x, y, w, h);
        this.direction = direction;
        this.min = min;
        this.step = step;
        this.max = max;
        buttonLess = new Button(0, 0, 0, 0);
        buttonMore = new Button(0, 0, 0, 0);
        buttonScroll = new Button(0, 0, 0, 0);
        addChild(buttonLess);
        addChild(buttonMore);
        addChild(buttonScroll);
        resize(w, h);

        profile = DefaultProfile.button;
    }

    public void resize(int w, int h){
        super.resize(w, h);
        int buttonSize = getH();
        if(direction == HORIZONTAL){
            buttonSize = getH();
        } else {
            buttonSize = getW();
        }
        resizeButtons(buttonSize);
        moveButtons(buttonSize);
    }

    void moveButtons(int buttonSize){
        buttonLess.move(0, 0);
        if(direction == HORIZONTAL){
            buttonMore.move(getW() - buttonSize, 0);
            buttonScroll.move(buttonSize, 0);
        } else {
            buttonMore.move(0, getH() - buttonSize);
            buttonScroll.move(0, buttonSize);
        }
    }

    void resizeButtons(int s){
        buttonLess.resize(s, s);
        buttonMore.resize(s, s);
        buttonScroll.resize(s, s);
    }

    public void move(){
        super.move(w, h);
    }

    public void holdEval(){
        super.holdEval();
        if(Iones.getCurrent().heldLeft == buttonScroll) {
            if (direction == HORIZONTAL) {
                int newX = PApplet.constrain(buttonScroll.getX() + PApplet.round((hov.mouseX - hov.pmouseX)/Iones.getScale()),
                        buttonLess.getW(), buttonMore.getX() - buttonScroll.getW());
                buttonScroll.move(newX, 0);
            } else {
                int newY = PApplet.constrain(buttonScroll.getY() + PApplet.round((hov.mouseY - hov.pmouseY)/Iones.getScale()),
                        buttonLess.getH(), buttonMore.getY() - buttonScroll.getH());
                buttonScroll.move(0, newY);

            }
            updateValue();
        } else if(Iones.getCurrent().heldLeft == buttonLess){
            value = PApplet.max(value - step, min);
            updateScrollButton();
        } else if(Iones.getCurrent().heldLeft == buttonMore){
            value = PApplet.min(value + step, max);
            updateScrollButton();
            PApplet.println("more");
        }
    }

    public void updateValue(){
        if(direction == HORIZONTAL) {
            value = PApplet.map(buttonScroll.getX(), buttonLess.getW(), buttonMore.getX()-buttonScroll.getW(), min, max);
        } else {
            value = PApplet.map(buttonScroll.getY(), buttonLess.getH(), buttonMore.getY()-buttonScroll.getH(), min, max);
        }
    }

    public float getValue(){
        return value;
    }
    public float getValueRelative(){
        return PApplet.map(value, min, max, 0, 1);
    }

    public void updateScrollButton(){
        if(min == max){
            return;
        }

        if(direction == HORIZONTAL){
            buttonScroll.move(PApplet.round(PApplet.map(getValue(), min, max, buttonLess.getW(), buttonMore.getX()-buttonScroll.getW())), 0);
        } else {
            buttonScroll.move(0, PApplet.round(PApplet.map(getValue(), min, max, buttonLess.getH(), buttonMore.getY()-buttonScroll.getH())));
        }
    }

    public void display(PGraphics p){
        p.fill(profile.getColor(Profile.STROKE));
        p.pushMatrix();
        p.translate(getX(), getY());
        if(direction == HORIZONTAL) {
            p.rect(buttonLess.getW(), 0, buttonMore.getX()-buttonLess.getW(), buttonMore.getH());
        } else {
            p.rect(0, buttonLess.getH(), buttonMore.getW(), buttonMore.getY()-buttonLess.getH());
        }
        p.popMatrix();
        super.display(p);
    }

    public void display(){
        display(hov.g);
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}

package iones;

public class Current{
    TextField textfield;
    MenuObject clickedLeft;
    MenuObject heldLeft;
    MenuObject clickedRight;
    MenuObject heldRight;
    MenuObject mousedOver;

    public boolean isCurrent(MenuObject m){
        return  m == textfield ||
                m == clickedLeft ||
                m == clickedRight ||
                m == heldLeft ||
                m == heldRight ||
                m == mousedOver;
    }

    public TextField getTextfield() {
        return textfield;
    }

    public void setTextfield(TextField t) {
        textfield = t;
    }

    public MenuObject getClickedLeft() {
        return clickedLeft;
    }

    public void setClickedLeft(MenuObject m) {
        clickedLeft = m;
    }

    public MenuObject getHeldLeft() {
        return heldLeft;
    }

    public void setHeldLeft(MenuObject m) {
        heldLeft = m;
    }

    public MenuObject getClickedRight() {
        return clickedRight;
    }

    public void setClickedRight(MenuObject m) {
        clickedRight = m;
    }

    public MenuObject getHeldRight() {
        return heldRight;
    }

    public void setHeldRight(MenuObject m) {
        heldRight = m;
    }

    public MenuObject getMousedOver() {
        return mousedOver;
    }

    public void setMousedOver(MenuObject m) {
        mousedOver = m;
    }

    Current(){

    }


}
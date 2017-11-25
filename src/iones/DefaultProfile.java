package iones;

public class DefaultProfile {
    public static Profile button;
    public static Profile menu;

    public static void initialize(){
        button = new Profile(0xff262626, 0xff4c4c4c, 0xffcccccc);
        button.setColor(Profile.FILL, Profile.HOVER, 0xff7f7f7f);
        button.setColor(Profile.FILL, Profile.CLICK, 0xff4499ff);
        menu = new Profile(0xff666666, 0xff333333, 0xffcccccc);
    }
}

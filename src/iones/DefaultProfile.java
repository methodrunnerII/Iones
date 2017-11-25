package iones;

public class DefaultProfile {
    public static Profile button;
    public static Profile menu;

    public static void initialize(){
        button = new Profile(0x262626ff, 0x4c4c4cff, 0xccccccff);
        button.setColor(Profile.FILL, Profile.HOVER, 0x7f7f7fff);
        button.setColor(Profile.FILL, Profile.CLICK, 0x4499ff);
        menu = new Profile(0x666666ff, 0x333333ff, 0xccccccff);
    }
}

import processing.core.PApplet;

public class Profile {
    static PApplet hov;

    public static void setPApplet(PApplet p){ hov = p; }

	public int MENUSTROKE;
	public int MENUFILL;
	
	public int BUTTONFILL;
	public int BUTTONSTROKE;
	public int BUTTONTEXT;
	public int BUTTONHOVER;
	public int BUTTONCLICK;
	
	public int MENUBLUE;
	public int CLEAR;
	
	Profile(){
	    MENUSTROKE = hov.color(0, 0, 0.4f);
        MENUFILL = hov.color(0, 0, 0.2f);

        BUTTONFILL = hov.color(0, 0, 0.3f);
        BUTTONSTROKE = hov.color(0, 0, 0.15f);
        BUTTONTEXT = hov.color(0, 0, 0.8f);
        BUTTONHOVER =  hov.color(0, 0, 0.5f);
        BUTTONCLICK = hov.color(0.15f, 1, 1);

        MENUBLUE = hov.color(0.6f, 0.4f, 0.3f);

        CLEAR = hov.color(0, 0, 0, 0.01f);
	}
}

package doctord;

import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class CinematicScene extends Scene {
	private ArrayList<Dialog> dialogs;
	private int currentDialog = 0, debounce = 0;
	java.awt.Font UIFont1;
    UnicodeFont uniFont;
	
	@SuppressWarnings("unchecked")
	public CinematicScene() {
		super();
		dialogs = new ArrayList<Dialog>();
		
		try {
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
			            org.newdawn.slick.util.ResourceLoader.getResourceAsStream("./res/fonts/manaspc.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 	UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 24.f);
	        uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
	        uniFont.addAsciiGlyphs();
	        ColorEffect a = new ColorEffect();
	        a.setColor(new java.awt.Color(255,255,255));

	        uniFont.getEffects().add(a);
	        try {
				uniFont.loadGlyphs();
			} catch (SlickException e) {
				e.printStackTrace();
			}
	}
	
	public void loadCinematic(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNextLine()) {
				dialogs.add(new Dialog(sc.nextLine()));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		if(debounce == 0 && doctorDGame.spaceBarIsDown()) {
			if(dialogs.get(currentDialog).finishedWriting()) {
				if(currentDialog < dialogs.size() - 1) {
					currentDialog++;
					debounce = 30;
				} else
					finished = true;
			} else {
				dialogs.get(currentDialog).writeAll();
				debounce = 30; 
			}
		} else if(debounce > 0)
			debounce--;
		dialogs.get(currentDialog).update();
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(uniFont);
		Animation a = dialogs.get(currentDialog).getAnimation();
		a.draw(0,0);
		
		g.setColor(Color.black);
		g.fillRect(0,720,1920,360);
		
		g.setColor(Color.white);
		g.drawString(dialogs.get(currentDialog).getText(),15,735);
	}
}

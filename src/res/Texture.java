package res;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Texture {
	
	public static Image loadTexture(String resource){
		
		try {
			return new ImageIcon(Texture.class.getResource(resource)).getImage();
		} catch (Exception e) {
			try {
				return new ImageIcon(Texture.class.getResource("/res/unknown.png")).getImage();
			} catch (Exception e1) {
				return null;
			}
		}
	}
	
	
}

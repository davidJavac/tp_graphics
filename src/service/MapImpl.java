package service;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.MapableImage;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class MapImpl extends MapableImage{

	private BitmapDisplay bd;
	
	@Override
	public Pixel[][] mapearImagen(BufferedImage imagen) {
		// TODO Auto-generated method stub
		int width = imagen.getWidth();
		int height = imagen.getHeight();
		
		Pixel [][] arrayP = new Pixel [height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				Color c = new Color(imagen.getRGB(j,i));
				
				arrayP[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue()); 
				
			}
		}
		
		return arrayP;
	}

	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}

}

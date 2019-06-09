package service;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.ColorImage;
import model.PixelDefault;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ColorImageImpl extends ColorImage{
	
	private BitmapDisplay bd;
	
	@Override
	public Pixel[][] escalaGrises(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				int grey_scale = (int)(imagen[i][j].getR() * 0.299 + 
						imagen[i][j].getR() * 0.587 + imagen[i][j].getR() * 0.114);
				int r = validRGB(grey_scale);
				int g = validRGB(grey_scale);
				int b = validRGB(grey_scale);
					
				img_return[i][j] = new Pixel(r, g, b); 
			}
		}
		return img_return;
	}

	@Override
	public Pixel[][] negativo(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				int r = validRGB(255 - imagen[i][j].getR());
				int g = validRGB(255 - imagen[i][j].getG());
				int b = validRGB(255 - imagen[i][j].getB());
				
				img_return[i][j] = new Pixel(r, g, b); 
			}
		}
		return img_return;
	}

	@Override
	public Pixel[][] brillo(Pixel[][] imagen, float nivel) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				int r = validRGB((int)(imagen[i][j].getR() + nivel)),
							g = validRGB((int)(imagen[i][j].getG() + nivel)), 
								b = validRGB((int)(imagen[i][j].getB() + nivel));
				img_return[i][j] = new Pixel(r, g, b); 
				  
			}
		}
		return img_return;
		
	}

	@Override
	public Pixel[][] contraste(Pixel[][] imagen, float contraste) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				int rojo = validRGB((int)(imagen[i][j].getR()* contraste));
				int verde = validRGB((int)(imagen[i][j].getG()* contraste));
				int azul = validRGB((int)(imagen[i][j].getB()* contraste));
			
				Pixel p = new Pixel(rojo, verde, azul);
				img_return[i][j] = p;

			}
		}
		return img_return;
	}

	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return value > 255 ? 255 : value < 0 ? 0 : value;
	}

	@Override
	public BufferedImage cuantification(BufferedImage bf, int q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}
	
	
}

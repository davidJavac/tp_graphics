package service;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.ColorImage;
import model.ColorImageImplDecorator;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ColorImageConvertImage extends ColorImageImplDecorator{

	private ColorImage color_img;
	private BitmapDisplay bd;
	public ColorImageConvertImage(ColorImage color_img) {
		
		this.color_img = color_img;
	}
	
	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return color_img.validRGB(value);
	}

	@Override
	public BufferedImage convertToBufferedImage(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int height = imagen.length;
		int width = imagen[0].length;
		
		BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				Color c = new Color(imagen[y][x].getR(), imagen[y][x].getG(), imagen[y][x].getB());
				bf.setRGB(x, y, c.getRGB());
			}
		}
		
		return bf;
	}

	@Override
	public Pixel[][] escalaGrises(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		return color_img.escalaGrises(imagen);
	}

	@Override
	public Pixel[][] negativo(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		return color_img.negativo(imagen);
	}

	@Override
	public Pixel[][] brillo(Pixel[][] imagen, float nivel) {
		// TODO Auto-generated method stub
		return color_img.brillo(imagen, nivel);
	}

	@Override
	public Pixel[][] contraste(Pixel[][] imagen, float contraste) {
		// TODO Auto-generated method stub
		return color_img.contraste(imagen, contraste);
	}

	@Override
	public BufferedImage cuantification(BufferedImage bf, int q) {
		// TODO Auto-generated method stub
		return color_img.cuantification(bf, q);
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}

}

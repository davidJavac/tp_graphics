package service;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.ResizableImage;
import model.ResizableImageImplDecorator;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ResizableConvertImage extends ResizableImageImplDecorator{

	private ResizableImage res;
	private BitmapDisplay bd;
	public ResizableConvertImage(ResizableImage res) {
		
		this.res = res;
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
	public Pixel[][] recorte(Pixel[][] imagen, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return res.recorte(imagen, x1, y1, x2, y2);
	}

	/*@Override
	public BufferedImage reduccion(BufferedImage bf, int x1, int y1) {
		// TODO Auto-generated method stub
		return res.reduccion(bf, x1, y1);
	}*/

	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return value > 255 ? 255 : value < 0 ? 0 : value;
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}

	@Override
	public Pixel[][] reduccion(Pixel[][] imagen, int x1, int y1) {
		// TODO Auto-generated method stub
		return res.reduccion(imagen, x1, y1);
	}

}

package service;

import model.NegativeValueException;
import model.ResizableImage;
import model.ResizableImageValidDecorator;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ResizableImageValid extends ResizableImageValidDecorator{

	private ResizableImage res;
	
	public ResizableImageValid(ResizableImage res) {
		
		this.res  = res;
	}
	
	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return res.validRGB(value);
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		res.setBitmapDisplay(bd);
	}

	@Override
	public void validPositiveValue(int x, int y)throws NegativeValueException{
		// TODO Auto-generated method stub
	
		if(x < 0 || y < 0) {
			
			throw new NegativeValueException();
		}
	}

	@Override
	public Pixel[][] recorte(Pixel[][] imagen, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return res.recorte(imagen, x1, y1, x2, y2);
	}

	@Override
	public Pixel[][] reduccion(Pixel[][] imagen, int x1, int y1) {
		// TODO Auto-generated method stub
		return res.reduccion(imagen, x1, y1)
				;
	}

}

package model;

import java.awt.image.BufferedImage;

import service.ResizableImageImpl;
import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ResizableImageImplDecorator extends ResizableImage{

	public abstract BufferedImage convertToBufferedImage(Pixel[][] imagen);
	
}

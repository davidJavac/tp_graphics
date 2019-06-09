package model;

import java.awt.image.BufferedImage;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ColorImageImplDecorator extends ColorImage{

	public abstract BufferedImage convertToBufferedImage(Pixel[][] imagen);
}

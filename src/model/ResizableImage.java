package model;

import java.awt.image.BufferedImage;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ResizableImage implements Valid, InclusiveBitmapDisplay{

	public abstract Pixel[][] recorte(Pixel[][] imagen, int x1, int y1, int x2, int y2);
	//public abstract BufferedImage reduccion(BufferedImage bf, int x1, int y1);
	public abstract Pixel[][] reduccion(Pixel[][] imagen, int x1, int y1);
}

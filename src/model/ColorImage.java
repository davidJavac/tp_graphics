package model;

import java.awt.image.BufferedImage;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ColorImage implements Valid, InclusiveBitmapDisplay{

	public abstract Pixel[][] escalaGrises(Pixel[][] imagen);
	public abstract Pixel[][] negativo(Pixel[][] imagen);
	public abstract Pixel[][] brillo(Pixel[][] imagen, float nivel);
	public abstract Pixel[][] contraste(Pixel[][] imagen, float contraste);
	public abstract BufferedImage cuantification(BufferedImage bf, int q);
}

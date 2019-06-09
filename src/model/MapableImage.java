package model;

import java.awt.image.BufferedImage;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class MapableImage implements Valid, InclusiveBitmapDisplay{

	public abstract Pixel[][] mapearImagen(BufferedImage imagen);
}

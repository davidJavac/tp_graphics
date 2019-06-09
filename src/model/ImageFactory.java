package model;

import java.awt.Image;
import java.io.FileInputStream;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ImageFactory {

	public abstract Image crearImagen(String path);
}

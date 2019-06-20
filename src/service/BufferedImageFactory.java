package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import model.ImageFactory;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class BufferedImageFactory extends ImageFactory{

	@Override
	public Image crearImagen(String path) {
		// TODO Auto-generated method stub
		FileInputStream archivo;
		try {
			archivo = new FileInputStream(path);
			try {
				//ImageInputStream imagen = ImageIO.createImageInputStream(archivo);
				return ImageIO.read(archivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

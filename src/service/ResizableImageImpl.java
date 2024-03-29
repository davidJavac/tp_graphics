package service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import model.PixelDefault;
import model.ResizableImage;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ResizableImageImpl extends ResizableImage{

	private BitmapDisplay bd;
	@Override
	public Pixel[][] recorte(Pixel[][] imagen, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
		int row = y1 > y2? y1-y2: y2 > y1? y2 - y1 : 0;
		int col = x1 > x2? x1-x2: x2 > x1? x2 - x1 : 0;
		
		int fila_min = imagen.length - y1;
		int colum_min = x1;
		
		Pixel [][] recorte = new Pixel [row][col];
		
		if((0 <= row && row <= imagen.length) && (0 <= col && col <= imagen[0].length) &&
				(row != 0 && col != 0)) {
			
			for(int i = 0;i < row; i++) {
				
				for(int j = 0;j < col; j++) {
					
					try {
						recorte[i][j] = imagen[i + fila_min][j + colum_min]; 
					}
					catch(ArrayIndexOutOfBoundsException e) {
						
						bd.showErrorMessage("Error de �ndice. Est� intentando acceder a una posici�n que no existe en el array");
						return PixelDefault.def(imagen);
					}
				}
			}
			
			return recorte;
			
		}
		else
			bd.showErrorMessage("Error al ingresar las coordenadas de recorte");
			
		return PixelDefault.def(imagen);
		
	}

	/*@Override
	public BufferedImage reduccion(BufferedImage bf, int x1, int y1) {
		// TODO Auto-generated method stub
		
		if(x1 > 0 && y1 > 0) {
			
			BufferedImage outputImage = new BufferedImage(x1,
					y1, bf.getType());
			
			// scales the input image to the output image
			Graphics2D g2d = outputImage.createGraphics();
			g2d.drawImage(bf, 0, 0, x1, y1, null);
			g2d.dispose();
			
			return outputImage;
			
		}
		else
			return bf;
		
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
		int width = imagen[0].length;
		int height = imagen.length;
		
		int reduc_width = (int) width / x1;
		int reduc_height = (int) height / y1;
		
		int row_aux = 0;
		Pixel[][] reduc_imag = new Pixel[reduc_height][reduc_width];
		for(int i = 0; i < height; i += y1) {
			
			int col_aux = 0;
			ArrayList<Pixel> ar_pixel = new ArrayList<Pixel>();
			for(int j = 0; j < width; j += x1) {
		
				int r = 0;
				int g = 0;
				int b = 0;
				if(row_aux < reduc_height && col_aux < reduc_width) {

					int aux_r = i;
					int aux_c = j;

					while(aux_r < i+y1) {
						
						while(aux_c < j+x1) {
							
							r += imagen[aux_r][aux_c].getR();
							g += imagen[aux_r][aux_c].getG();
							b += imagen[aux_r][aux_c].getB();
							aux_c++;
						}
		
						aux_r++;
					}
					int color_r = validRGB(r/x1);
					int color_g = validRGB(g/x1);
					int color_b = validRGB(b/x1);
					
					Pixel p = new Pixel(color_r,color_g,color_b);
					reduc_imag[row_aux][col_aux] = p; 
					 
				}
					
				col_aux++;
			}
			row_aux++;
		}
		
		return reduc_imag;
	}

}

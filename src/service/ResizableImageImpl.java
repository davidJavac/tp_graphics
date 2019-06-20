package service;

import model.PixelDefault;
import model.ResizableImage;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ResizableImageImpl extends ResizableImage{

	private BitmapDisplay bd;
	@Override
	public Pixel[][] recorte(Pixel[][] imagen, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
		int row = y2 - y1;
		int col = x2 - x1;
				
		if((0 <= row && row <= imagen.length) && (0 <= col && col <= imagen[0].length) &&
				(row != 0 && col != 0)) {
			Pixel [][] recorte = new Pixel [row][col];
			
			for(int i = 0;i < row; i++) {
				
				for(int j = 0;j < col; j++) {
					
					try {
						recorte[i][j] = imagen[i + y1][j + x1]; 
					}
					catch(ArrayIndexOutOfBoundsException e) {
						
						bd.showErrorMessage("Error de índice. Está intentando acceder a una posición que no existe en el array");
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

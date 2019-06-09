package service;

import model.TurnImage;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class TurnImageImpl extends TurnImage{

	private BitmapDisplay bd;
	
	@Override
	public Pixel[][] espejarHorizontal(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] arrayP = new Pixel [height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				arrayP[i][width - 1 - j] = imagen[i][j]; 
			}
		}
		return arrayP;
	}

	@Override
	public Pixel[][] espejarVertical(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] arrayP = new Pixel [height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				arrayP[height - 1 -i][j] = imagen[i][j]; 
			}
		}
		return arrayP;

	}

	@Override
	public Pixel[][] rotar90(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] arrayP = new Pixel [width][height];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
//				arrayP[i][height - 1 -j] = imagen[i][j]; 
				arrayP[width - 1 -j][i] = imagen[i][j]; 
			}
		}
		return arrayP;

	}

	@Override
	public Pixel[][] rotar180(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] arrayP = new Pixel [height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				arrayP[height - 1 -i][width - 1 - j] = imagen[i][j]; 
			}
		}
		return arrayP;

	}

	@Override
	public Pixel[][] rotar270(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] arrayP = new Pixel [width][height];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				arrayP[j][height - 1 - i] = imagen[i][j]; 
			}
		}
		return arrayP;

	}

	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}

}

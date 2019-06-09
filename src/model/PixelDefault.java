package model;

import unpaz.ayp3.bitmapDisplay.Pixel;

public  class PixelDefault extends Pixel{

	private static Pixel[][] DEFAULT;
	
	public PixelDefault(int r, int g, int b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}	

    public static Pixel[][] def(Pixel[][] imagen){
    	DEFAULT = new Pixel[imagen.length][imagen[0].length];
    	for(int i = 0; i < imagen.length; i++) 
    		for(int j = 0; j < imagen[0].length; j++) 
    			DEFAULT[i][j] = new PixelDefault(255,255,255);
    	return DEFAULT;
    }

}

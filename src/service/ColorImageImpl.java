package service;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.ColorImage;
import model.PixelDefault;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class ColorImageImpl extends ColorImage{
	
	private BitmapDisplay bd;
	
	@Override
	public Pixel[][] escalaGrises(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				int grey_scale = (int)(imagen[i][j].getR() * 0.299 + 
						imagen[i][j].getR() * 0.587 + imagen[i][j].getR() * 0.114);
				int r = validRGB(grey_scale);
				int g = validRGB(grey_scale);
				int b = validRGB(grey_scale);
					
				img_return[i][j] = new Pixel(r, g, b); 
			}
		}
		return img_return;
	}

	@Override
	public Pixel[][] negativo(Pixel[][] imagen) {
		// TODO Auto-generated method stub
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
				int r = validRGB(255 - imagen[i][j].getR());
				int g = validRGB(255 - imagen[i][j].getG());
				int b = validRGB(255 - imagen[i][j].getB());
				
				img_return[i][j] = new Pixel(r, g, b); 
			}
		}
		return img_return;
	}

	@Override
	public Pixel[][] brillo(Pixel[][] imagen, float nivel) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				int r = validRGB((int)(imagen[i][j].getR() + nivel)),
							g = validRGB((int)(imagen[i][j].getG() + nivel)), 
								b = validRGB((int)(imagen[i][j].getB() + nivel));
				/*int r = imagen[i][j].getR(), g = imagen[i][j].getG(), b = imagen[i][j].getB();
				
				 float hsbVals[] = Color.RGBtoHSB( r,
                         g,
                         b, null );

				
				 hsbVals[2] = nivel > 0 ? Math.min(1.0f, hsbVals[2] + nivel/100f) : 
					 Math.max(0.0f, hsbVals[2] + nivel/100f);
				 
				 Color highlight = Color.getHSBColor( hsbVals[0], hsbVals[1], hsbVals[2]); 
 
				 img_return[i][j] = new Pixel(highlight.getRed(), highlight.getGreen(),
						 highlight.getBlue()); */
				 img_return[i][j] = new Pixel(r, g, b); 
				  
			}
		}
		return img_return;
		
	}

	@Override
	public Pixel[][] contraste(Pixel[][] imagen, float contraste) {
		// TODO Auto-generated method stub
		
		int width = imagen[0].length;
		int height = imagen.length;
		Pixel [][] img_return = new Pixel[height][width];
		
		//contraste = (float) Math.pow((100 + contraste) / 100, 2);
		for(int i = 0;i < height; i++) {
			
			for(int j = 0;j < width; j++) {
				
/*				int rojo = validRGB((int)(((((imagen[i][j].getR() / 255.0) - 0.5) * contraste) + 0.5) * 255.0));
				 
				int verde = validRGB((int)(((((imagen[i][j].getG() / 255.0) - 0.5) * contraste) + 0.5) * 255.0));
				
				int azul = validRGB((int)(((((imagen[i][j].getB() / 255.0) - 0.5) * contraste) + 0.5) * 255.0));*/
				int rojo = validRGB((int)(imagen[i][j].getR()* contraste));
				int verde = validRGB((int)(imagen[i][j].getG()* contraste));
				int azul = validRGB((int)(imagen[i][j].getB()* contraste));
			
				Pixel p = new Pixel(rojo, verde, azul);
				img_return[i][j] = p;

			}
		}
		return img_return;
	}

	@Override
	public int validRGB(int value) {
		// TODO Auto-generated method stub
		return value > 255 ? 255 : value < 0 ? 0 : value;
	}

	@Override
	public BufferedImage cuantification(BufferedImage bf, int q) {
		// TODO Auto-generated method stub
		
		int mask = 0;
		for (int i = 0; i < q; i ++) {

			mask = (1 << q) - 1;
		}
		//int mask = (1 << q) - 1;
		
		/* public static final int MASK_0 = 0x00800000; // 0 bits per channel (except red)
		    public static final int MASK_1 = 0xff808080; // 1 bit per channel
		    public static final int MASK_2 = 0xffc0c0c0; // 2 bits per channel
		    public static final int MASK_3 = 0xffe0e0e0; // 3 bits per channel
		    public static final int MASK_4 = 0xfff0f0f0; // 4 bits per channel
		*/
		int r = (q >> 16) & 0xFF;
        int g = (q>> 8) & 0xFF;
        int b = (q>> 0) & 0xFF;
		
		
		int w = bf.getWidth();
	    int h = bf.getHeight();
        // Create result image
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
        // Go through every pixel of the image
        for(int x=0; x< w; x++){
            for(int y=0; y< h; y++){
            	
                // Apply mask to original value and save it in result image
                result.setRGB(x,y, bf.getRGB(x, y) & mask);
            }
        }
        return result;
	}

	@Override
	public void setBitmapDisplay(BitmapDisplay bd) {
		// TODO Auto-generated method stub
		this.bd = bd;
	}
	
	
}

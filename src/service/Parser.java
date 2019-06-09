package service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import model.Application;
import model.Validation;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class Parser implements Validation{
	
	private Application app;
	private	Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public Map<String, Object> visit(Application app) {
		// TODO Auto-generated method stub
		
		this.app = app;
		map.put("error", false);
		
		switch(app.getOperacion()) {
		
		case "crop": return crop();
		case "modifyBrightness" : return modifyBrightness();
		case "reduce" : return reduce();
		case "modifyContrast" : return modifyContrast();
		case "quantization" : return quantization();
		case "save" : return save(app.getImage_result());
		}
		return map;
	}

	public Map<String, Object> crop(){
		
		String coordenadas [] = {};
		if(app.getComando()!=null) {
			
			coordenadas = app.getComando().replaceAll("[)(]", "").split(";");
			
		}
		
		try {
			String sup_izq [] = coordenadas[0].split(",");
			String inf_der [] = coordenadas[1].split(",");
			int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
			
			x1 = Integer.parseInt(sup_izq[0]);
			y1 = Integer.parseInt(sup_izq[1]); 
			x2 = Integer.parseInt(inf_der[0]);
			y2 = Integer.parseInt(inf_der[1]); 
			
			validPositive(x1);
			validPositive(y1);
			validPositive(x2);
			validPositive(y2);
			
			map.put("x1", x1);
			map.put("y1", y1);
			map.put("x2", x2);
			map.put("y2", y2);
			
			
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("Deben introducirse valores para x1 y1 x2 y2");
			
		}
		catch(NumberFormatException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("Los valores de las coordenadas no son numéricos");
			
		}
		catch(IllegalArgumentException e) {
			map.put("error", true);
			app.getBd().showErrorMessage("Solo se aceptan valores positivos para esta funcionalidad");
			
		}
		
		return map;
		
	}
	
	public Map<String, Object> modifyBrightness(){
		
		try {
			
			float bright = Float.parseFloat(app.getComando());
			map.put("bright", bright);
		}
		catch(NumberFormatException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("El valor del brillo no es numérico");

		}
		
		return map;
	}

	public Map<String, Object> modifyContrast(){
		
		try {
			
			float contrast = Float.parseFloat(app.getComando());
			map.put("contrast", contrast);
		}
		catch(NumberFormatException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("El valor del contraste no es numérico");

		}
		
		return map;
	}
	
	public Map<String, Object> reduce(){
		
		String fac_escalas [] = {};
		if(app.getComando()!=null) {
			
			fac_escalas = app.getComando().replaceAll("[)(]", "").split(",");
			
		}
		try {
			
			int sx = Integer.parseInt(fac_escalas[0]);
			map.put("sx", sx);
			int sy = Integer.parseInt(fac_escalas[1]);
			map.put("sy", sy);
			//validPositive(sx);
			//validPositive(sy);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("Deben introducirse valores para sx y sy");
			
		}
		catch(NumberFormatException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("Los valores de las escalas no son numéricos o no respetan el formato predeterminado");

		}
		catch(IllegalArgumentException e) {
			map.put("error", true);
			app.getBd().showErrorMessage("Solo se aceptan valores positivos para esta funcionalidad");
			
		}
		
		
		return map;
		
	}

	public Map<String, Object> quantization(){
		
		try {
			
			int q = Integer.parseInt(app.getComando());
			
			map.put("q", q);
		}
		catch(NumberFormatException e) {
			
			map.put("error", true);
			app.getBd().showErrorMessage("El valor de q no es numérico");
			
		}
		
		return map;
	}
	
	public Map<String, Object> save(Pixel[][] imagen){
		
		int height = imagen.length;
		int width = imagen[0].length;
		
		BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				Color c = new Color(imagen[y][x].getR(), imagen[y][x].getG(), imagen[y][x].getB());
				bf.setRGB(x, y, c.getRGB());
			}
		}
		map.put("buffered_image", bf);
		return map;
		
	}
	
	public void validPositive(int num) {
		
		if(num < 0)
			throw new IllegalArgumentException();
		
	}
	
}

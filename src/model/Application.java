package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import service.BufferedImageFactory;
import service.ColorImageImpl;
import service.MapImpl;
import service.Parser;
import service.ResizableImageImpl;
import service.ResizableImageValid;
import service.SystemValidation;
import service.TurnImageImpl;
import unpaz.ayp3.bitmapDisplay.BitmapDisplay;
import unpaz.ayp3.bitmapDisplay.ImagesOperationsListener;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class Application implements ImagesOperationsListener, Visitable{

	private Pixel[][] image;
	private Pixel[][] image_result;
	private static BitmapDisplay bd;
	private MapableImage map_img;
	private ColorImage color_img;
	private TurnImage turn_img;
	private ResizableImage res_img;
	private Validation parser;
	private Validation sysVal;
	private Map<String, Object> map_parser;
	private Map<String, Object> map_sysVal;
	private String comando;
	private String operacion;
	
	public String getComando() {
		return comando;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public Application() {
		//this.image = new Pixel[500][500];
		this.map_img = new MapImpl();
		this.color_img = new ColorImageImpl();
		this.turn_img = new TurnImageImpl();
		this.res_img = new ResizableImageImpl();
		this.parser = new Parser();
		this.sysVal = new SystemValidation();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		bd = new BitmapDisplay(new Application());
		bd.showOriginal(PixelDefault.def(new Pixel[500][500]), 500, 500);
	
	}

	public static BitmapDisplay getBd() {
		return bd;
	}
	
	@Override
	public void anotherFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateHistogram() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crop(String arg0) {
		// TODO Auto-generated method stub
		
		setComando(arg0);
		setOperacion("crop");
		map_sysVal = this.accept(sysVal);
		
		if(!(boolean)map_sysVal.get("error")) {
			map_parser = this.accept(parser);
			if(!(boolean)map_parser.get("error")) {
			
				image_result = this.res_img.recorte(image, 
						(int)map_parser.get("x1"), (int)map_parser.get("y1"), 
						(int)map_parser.get("x2"), (int)map_parser.get("y2")); 
				
				bd.showResult(image_result,image_result[0].length, image_result.length);
				
			}	
		}
			
			
	}

	@Override
	public void filter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flipHorizontal() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean) map_sysVal.get("error")) {
			image_result = turn_img.espejarHorizontal(image); 
			bd.showResult(image_result, image_result[0].length, image_result.length);
			
		}
	}

	@Override
	public void flipVertical() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean) map_sysVal.get("error")) {
			image_result= turn_img.espejarVertical(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
		}
		
	}

	@Override
	public void grayscale() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean) map_sysVal.get("error")) {

			image_result = color_img.escalaGrises(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
			
		}

	}

	@Override
	public void loadImage(String arg0) {
		// TODO Auto-generated method stub
		linkBitmapDisplay();
		ImageFactory img_fac = new BufferedImageFactory();
		BufferedImage lectura = (BufferedImage) img_fac.crearImagen(arg0);
		image = map_img.mapearImagen(lectura);
		bd.showOriginal(image, image[0].length, image.length);
	}

	@Override
	public void modifyBrightness(String arg0) {
		// TODO Auto-generated method stub
		setComando(arg0);
		setOperacion("modifyBrightness");
		map_sysVal = this.accept(sysVal);

		if(!(boolean)map_sysVal.get("error")) {
			map_parser = this.accept(parser);
			if(!(boolean)map_parser.get("error")) {

				image_result = color_img.brillo(image, (float)map_parser.get("bright"));
				bd.showResult(image_result,image_result[0].length,image_result.length);
			}
		}
	}

	@Override
	public void modifyContrast(String arg0) {
		// TODO Auto-generated method stub
		setComando(arg0);
		setOperacion("modifyContrast");
		map_sysVal = this.accept(sysVal);
		
		if(!(boolean)map_sysVal.get("error")) {
			map_parser = this.accept(parser);
			if(!(boolean)map_parser.get("error")) {
			
				image_result=  color_img.contraste(image, (float)map_parser.get("contrast"));
				bd.showResult(image_result,image_result[0].length,image_result.length);
			}
		}
				
	}

	@Override
	public void negative() {
		// TODO Auto-generated method stub
		
		map_sysVal = this.accept(sysVal);
		if(!(boolean) map_sysVal.get("error")) {
			
			image_result = color_img.negativo(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
			
		}
	}

	@Override
	public void quantization(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public Pixel[][] getImage() {
		return image;
	}

	@Override
	public void reduce(String arg0) {
		// TODO Auto-generated method stub
		setComando(arg0);
		setOperacion("reduce");
		map_sysVal = this.accept(sysVal);
		
		if(!(boolean)map_sysVal.get("error")) {
			map_parser = this.accept(parser);
			if(!(boolean)map_parser.get("error")) {
			
				int sx = (int) map_parser.get("sx");
				int sy = (int) map_parser.get("sy");
	
				ResizableImageValidDecorator  res_dec = new ResizableImageValid(res_img);
				
				try {
					res_dec.validPositiveValue(sx, sy);
					image_result = res_dec.reduccion(image, sx, sy);
					bd.showResult(image_result, image_result[0].length, image_result.length);
				} catch (NegativeValueException e) {
					// TODO Auto-generated catch block
					bd.showErrorMessage(e.toString());
				}
			}
		}
		
	}

	@Override
	public void rotate180() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean)map_sysVal.get("error")) {
				
			image_result= turn_img.rotar180(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
				
		}
		
	}

	@Override
	public void rotate270() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean)map_sysVal.get("error")) {
				
			image_result = turn_img.rotar270(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
			
		}
		
	}

	@Override
	public void rotate90() {
		// TODO Auto-generated method stub
		map_sysVal = this.accept(sysVal);
		if(!(boolean)map_sysVal.get("error")) {
			image_result = turn_img.rotar90(image);
			bd.showResult(image_result, image_result[0].length, image_result.length);
			
		}
		
	}

	@Override
	public void saveResult(String arg0) {
		// TODO Auto-generated method stub
		File outputfile = new File(arg0);
		this.setOperacion("save");
		map_sysVal = this.accept(sysVal);
		if(!(boolean)map_sysVal.get("error")) {
			
			map_parser = this.accept(parser);
			if(!(boolean)map_parser.get("error")) {
				
				BufferedImage bf = (BufferedImage) map_parser.get("buffered_image"); 
				try {
					System.out.println(ImageIO.write(bf, "bmp", outputfile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					bd.showErrorMessage("No se ha podido guardar el archivo");
				}
				bd.showInformationMessage("El archivo se ha guardado con éxito");
			}
		}
		
	}

	public Pixel[][] getImage_result() {
		return image_result;
	}

	@Override
	public Map<String, Object> accept(Validation parseable) {
		// TODO Auto-generated method stub
		return parseable.visit(this);
	}
	
	public void linkBitmapDisplay() {
		
		map_img.setBitmapDisplay(bd);
		turn_img.setBitmapDisplay(bd);
		color_img.setBitmapDisplay(bd);
		res_img.setBitmapDisplay(bd);
	}

}

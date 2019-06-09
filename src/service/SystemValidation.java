package service;

import java.util.HashMap;
import java.util.Map;

import model.Application;
import model.Validation;
import unpaz.ayp3.bitmapDisplay.Pixel;

public class SystemValidation implements Validation{

	@Override
	public Map<String, Object> visit(Application app) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
		
			validNotNull(app.getImage());
			map.put("error", false);
				
		}
		catch(NullPointerException e) {
			map.put("error", true);
			app.getBd().showErrorMessage("Debe cargar una imagen antes de realizar alguna operación");
		}
		return map;
	}

	public void validNotNull(Pixel[][] imagen) {
		
		if(imagen == null)
			throw new NullPointerException();
		
	}
	
	
}

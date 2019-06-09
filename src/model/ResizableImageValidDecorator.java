package model;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class ResizableImageValidDecorator extends ResizableImage{

	public abstract void validPositiveValue(int x, int y) throws NegativeValueException;
}

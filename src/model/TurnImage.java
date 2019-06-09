package model;

import unpaz.ayp3.bitmapDisplay.Pixel;

public abstract class TurnImage implements Valid, InclusiveBitmapDisplay{

	public abstract Pixel[][] espejarHorizontal(Pixel[][] imagen);
	public abstract Pixel[][] espejarVertical(Pixel[][] imagen);
	public abstract Pixel[][] rotar90(Pixel[][] imagen);
	public abstract Pixel[][] rotar180(Pixel[][] imagen);
	public abstract Pixel[][] rotar270(Pixel[][] imagen);
}

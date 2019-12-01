package com.LADLAB.LADLAB.Level.Tile;

import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;

public class Tile {
	
	public Sprite sprite;
	
	//grass
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile darkGrass = new GrassTile(Sprite.darkGrass);
	//other
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile waterTile = new WaterTile(Sprite.waterTile);
	public static Tile wood = new GrassTile(Sprite.wood);
	public static Tile brick = new BrickTile(Sprite.brick);
	public static Tile bush = new BushTile(Sprite.bush);
	public static Tile sand = new SandTile(Sprite.sand);
	public static Tile fire = new Fire(Sprite.fire);
	public static Tile tombstone = new TombStone(Sprite.tombstone1);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void paint(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}

}

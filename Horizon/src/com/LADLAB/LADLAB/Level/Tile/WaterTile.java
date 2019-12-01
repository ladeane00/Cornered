package com.LADLAB.LADLAB.Level.Tile;

import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;

public class WaterTile extends Tile {

	public WaterTile(Sprite sprite) {
		super(sprite);
	}
	
	public void paint(int x, int y, Screen screen) {
		screen.paintTile(x << 4, y << 4, sprite);
	}

}

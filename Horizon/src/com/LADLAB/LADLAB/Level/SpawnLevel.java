package com.LADLAB.LADLAB.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.LADLAB.LADLAB.Entity.Mob.Chaser;
import com.LADLAB.LADLAB.Entity.Mob.Skeleton;
import com.LADLAB.LADLAB.Entity.Mob.Slime;
import com.LADLAB.LADLAB.Entity.Mob.Wraith;
import com.LADLAB.LADLAB.Level.Tile.Tile;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
		
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResourceAsStream(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(new Slime(25, 25));
		for (int i = 0; i < 50; i++) {
		add(new Skeleton(25, 25));
		}
		add(new Chaser(25, 25));
		for (int i = 0; i < 10; i++) {
		add(new Wraith(25, 25));
		}
	}
	
	// Grass = 0xFF00
	protected void generateLevel() {
	}

}

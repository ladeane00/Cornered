package com.LADLAB.LADLAB.Graphics;

import java.util.Random;

import com.LADLAB.LADLAB.Entity.Mob.Mob;
import com.LADLAB.LADLAB.Entity.Mob.Player;
import com.LADLAB.LADLAB.Entity.Projectile.Projectile;
import com.LADLAB.LADLAB.Level.Tile.Tile;

public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	public int xOffset, yOffset;
	
	Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void paintSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) { 
		  if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		  }
		  for (int y = 0; y < sheet.HEIGHT; y++) {
			  int ya = y + yp;
				for (int x = 0; x < sheet.WIDTH; x++) {
					int xa = x + xp;
					if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
					pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
				}
			}
		}
	
	public void paintSprite(int xp, int yp, Sprite sprite, boolean fixed) { 
	  if (fixed) {
		xp -= xOffset;
		yp -= yOffset;
	  }
	  for (int y = 0; y < sprite.getHeight(); y++) {
		  int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	public void paintTile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
	    for (int y = 0; y < sprite.SIZE; y++) {
	    	int ya = y + yp;
	    	for (int x = 0; x < sprite.SIZE; x++) {
	    		int xa = x + xp;
	    		if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
	    		if (xa < 0) xa = 0;
	    		pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
	    	}
	    }
	}
	
	public void paintProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
	    for (int y = 0; y < p.getProjectileSize(); y++) {
	    	int ya = y + yp;
	    	for (int x = 0; x < p.getProjectileSize(); x++) {
	    		int xa = x + xp;
	    		if (xa < -p.getProjectileSize() || xa >= width || ya < 0 || ya >= height) break;
	    		if (xa < 0) xa = 0;
	    		int col = p.getProjectileSprite().pixels[x + y * p.getProjectileSprite().SIZE];
	    		if (col != 0xff6CD94E) pixels[xa + ya * width] = col;
	    	}
	    }
	}
	
	public void paintMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
	    for (int y = 0; y < 16; y++) {
	    	int ya = y + yp;
	    	int ys = y;
	    	for (int x = 0; x < 16; x++) {
	    		int xa = x + xp;
	    		int xs = x;
	    		if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
	    		if (xa < 0) xa = 0;
	    		int color = mob.getSprite().pixels[xs + ys * 16];
	    		if (color != 0xff6CD94E) pixels[xa + ya * width] = color;
	    	}
	    }
	}
	
	public void paintMob(int xp, int yp, Sprite sprite, boolean flip, int spriteSize) {
		xp -= xOffset;
		yp -= yOffset;
	    for (int y = 0; y < spriteSize; y++) {
	    	int ya = y + yp;
	    	int ys = y;
	    	for (int x = 0; x < spriteSize; x++) {
	    		int xa = x + xp;
	    		int xs = x;
	    		if (flip == true) xs = spriteSize - 1 - x;
	    		if (xa < -spriteSize || xa >= width || ya < 0 || ya >= height) break;
	    		if (xa < 0) xa = 0;
	    		int color = sprite.pixels[xs + ys * spriteSize];
	    		if (color != 0xff6CD94E) pixels[xa + ya * width] = color;
	    	}
	    }
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}

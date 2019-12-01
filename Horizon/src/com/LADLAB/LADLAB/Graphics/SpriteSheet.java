package com.LADLAB.LADLAB.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/SpriteSheet.png", 256);
	
	public static SpriteSheet player = new SpriteSheet("/textures/PlayerSheet.png", 256);
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 2, 16);
	public static SpriteSheet player_down = new SpriteSheet(player, 1, 0, 1, 2, 16);
	public static SpriteSheet player_right = new SpriteSheet(player, 2, 0, 1, 2, 16);
	public static SpriteSheet player_left = new SpriteSheet(player, 3, 0, 1, 2, 16);
	public static SpriteSheet player_shoot = new SpriteSheet(player, 4, 0, 1, 2, 16);
	
	public static SpriteSheet skeleton = new SpriteSheet("/textures/SkeletonSheet.png", 32);
	public static SpriteSheet skeleton_right = new SpriteSheet(skeleton, 0, 0, 1, 2, 16);
	public static SpriteSheet skeleton_left = new SpriteSheet(skeleton, 1, 0, 1, 2, 16);
	
	public static SpriteSheet slime = new SpriteSheet("/textures/SlimeSheet.png", 64);
	public static SpriteSheet slime_right = new SpriteSheet(slime, 0, 0, 1, 2, 32);
	public static SpriteSheet slime_left = new SpriteSheet(slime, 1, 0, 1, 2, 32);
	
	public static SpriteSheet wraith = new SpriteSheet("/textures/WraithSheet.png", 32);
	public static SpriteSheet wraith_right = new SpriteSheet(wraith, 0, 0, 1, 2, 16);
	public static SpriteSheet wraith_left = new SpriteSheet(wraith, 1, 0, 1, 2, 16);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spritesize) {
		int xx = x * spritesize;
		int yy = y * spritesize;
		int w = width * spritesize;
		int h = height * spritesize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
		    for (int xa = 0; xa < width; xa++) {
		    	int[] spritePixels = new int[spritesize * spritesize];
		    	for (int y0 = 0; y0 < spritesize; y0++) {
		    		for (int x0 = 0; x0 < spritesize; x0++) {
		    			spritePixels[x0 + y0 * spritesize] = pixels[(x0 + xa * spritesize) + (y0 + ya * spritesize) * WIDTH];
		    		}
		    	}
		    	Sprite sprite = new Sprite(spritePixels, spritesize, spritesize);
		    	sprites[frame++] = sprite;
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH =size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	public Sprite[] getSprite() {
		return sprites;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

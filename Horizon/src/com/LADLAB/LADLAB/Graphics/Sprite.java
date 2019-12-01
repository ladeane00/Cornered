package com.LADLAB.LADLAB.Graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite waterTile = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite wood = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite bush = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite sand = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite tombstone1 = new Sprite(16, 7, 0, SpriteSheet.tiles);
	public static Sprite darkGrass = new Sprite(16, 8, 0, SpriteSheet.tiles);
	public static Sprite purpleGrass = new Sprite(16, 9, 0, SpriteSheet.tiles);
	
	public static Sprite playerForward = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite playerBackward = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite playerRight = new Sprite(16, 2, 1, SpriteSheet.tiles);
	
	public static Sprite playerForward1 = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite playerBackward1 = new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite playerRight1 = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite playerShoot = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite fire = new Sprite(16, 5, 1, SpriteSheet.tiles);
	
	public static Sprite wizardProjectile = new Sprite(16, 6, 1, SpriteSheet.tiles);
	
	public static Sprite particle_1 = new Sprite(3, 0xffff0000);
	public static Sprite particle_2 = new Sprite(3, 0xfff0ff00);
	public static Sprite particle_3 = new Sprite(3, 0xffffb400);
	
	public static Sprite skeleton = new Sprite(16, 0, 0, SpriteSheet.skeleton);
	
	public static Sprite wraith = new Sprite(16, 0, 0, SpriteSheet.wraith);
	
	public static Sprite slime = new Sprite(32, 0, 0, SpriteSheet.slime);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
    public Sprite(int width, int height, int color) {
    	SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
}

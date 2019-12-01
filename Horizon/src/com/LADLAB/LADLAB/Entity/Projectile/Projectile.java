package com.LADLAB.LADLAB.Entity.Projectile;

import java.util.Random;

import com.LADLAB.LADLAB.Entity.Entity;
import com.LADLAB.LADLAB.Graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double speed, range, damage;
	
	protected final Random r = new Random();
	
	
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		
		this.x = x;
		this.y = y;
	}
	
	public Sprite getProjectileSprite() {
		return sprite;
	}
	
	public int getProjectileSize() {
		return sprite.SIZE;
	}
	
	protected void move() {
		
	}

}

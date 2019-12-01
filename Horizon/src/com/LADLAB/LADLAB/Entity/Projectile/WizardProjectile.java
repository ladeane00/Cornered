package com.LADLAB.LADLAB.Entity.Projectile;

import com.LADLAB.LADLAB.Entity.Entity;
import com.LADLAB.LADLAB.Entity.Spawner.ParticleSpawner;
import com.LADLAB.LADLAB.Entity.Spawner.Spawner;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;

public class WizardProjectile extends Projectile {
	
	public static final int RATE = 10;

	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = r.nextInt(20) + 100;
		speed = 3;
		damage = 20;
		sprite = Sprite.wizardProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		//System.out.println("" + nx + " " + ny);
	}
	
	public void tick() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 4, 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 100, 50, level));
			remove();
		}
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) {
			remove();
		}
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public void paint(Screen screen) {
		screen.paintProjectile((int)x, (int)y, this);
	}
	
	

}

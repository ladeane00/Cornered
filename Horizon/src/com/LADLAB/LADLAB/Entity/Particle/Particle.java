package com.LADLAB.LADLAB.Entity.Particle;

import java.util.ArrayList;

import com.LADLAB.LADLAB.Entity.Entity;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	private Sprite sprite2;
	private Sprite sprite3;
	
	private int life;
	private int time  = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_1;
		sprite2 = Sprite.particle_2;
		sprite3 = Sprite.particle_3;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public void tick() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		za -= 0.1;
		
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16 + 2) / 16;
			double yt = (y - c / 2 * 16 + 2) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
		}

	public void paint(Screen screen) {
		int var = random.nextInt(2);
		if (var == 0) {
		screen.paintSprite((int)xx, (int)yy - (int) zz, sprite, true);
		}
		if (var == 1) {
			screen.paintSprite((int)xx, (int)yy - (int) zz, sprite2, true);
			}
		if (var == 2) {
			screen.paintSprite((int)xx, (int)yy - (int) zz, sprite3, true);
			}
	}

}

package com.LADLAB.LADLAB.Entity.Mob;

import java.util.ArrayList;

import com.LADLAB.LADLAB.Game;
import com.LADLAB.LADLAB.Entity.Entity;
import com.LADLAB.LADLAB.Entity.Projectile.Projectile;
import com.LADLAB.LADLAB.Entity.Projectile.WizardProjectile;
import com.LADLAB.LADLAB.Graphics.AnimatedSprite;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;
import com.LADLAB.LADLAB.Graphics.SpriteSheet;
import com.LADLAB.LADLAB.Input.KeyBoard;
import com.LADLAB.LADLAB.Input.Mouse;

public class Player extends Mob {
	
	private KeyBoard input;
	private Sprite sprite;
	private Projectile p;
	private int animation = 0;
	private boolean walking = false;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16, 32, 2);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 32, 2);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16, 32, 2);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16, 32, 2);
	private AnimatedSprite shoot = new AnimatedSprite(SpriteSheet.player_shoot, 16, 32, 2);
	
	private AnimatedSprite animSprite = down;
	
	private boolean shooting;
	
	private int fireRate = 0;
	
	public Player(KeyBoard input) {
		this.input = input;
		sprite = Sprite.playerForward;
		animSprite = down;
	}
	
	public Player(int x, int y, KeyBoard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		dir = Direction.DOWN;
		fireRate = WizardProjectile.RATE;
		shooting = false;
	}
	
	public void tick() {
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
		double xa = 0;
		double ya = 0;
		double speed = 1.0;
		if (animation < 7500) animation++;
		else animation = 0;
		if (input.up) {
			animSprite = up;
			ya-= speed;
		}
		if (input.down) {
			animSprite = down;
			ya+= speed;
		}
		if (input.left) {
			animSprite = left;
			xa-= speed;
		}
		if (input.right) {
			animSprite = right;
			xa+= speed;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
		    walking = true;
		} else {
			walking = false;
		}
		
		clear();
		tickShooting();
		
	}
	
	private void clear() {
	    for (int i = 0; i < level.getProjectiles().size(); i++) {
	    	Projectile p = level.getProjectiles().get(i);
	    	if (p.isRemoved()) level.getProjectiles().remove(i);
	    }
		
	}

	private void tickShooting() {
		if (Mouse.getB() == 1) {
			shooting = true;
		} else {
			shooting = false;
		}
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - 536;
			double dy = Mouse.getY() - 273.5;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.RATE;
		}
	}
	
	public void paint(Screen screen) {
		boolean flip = false;
		
		if (shooting) {
			animSprite = shoot;
		}
		sprite = animSprite.getSprite();
		screen.paintMob((int)(x - 16), (int)(y - 16), sprite, flip, 16);
	}

}

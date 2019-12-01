package com.LADLAB.LADLAB.Entity.Mob;

import java.util.ArrayList;

import com.LADLAB.LADLAB.Entity.Mob.Mob.Direction;
import com.LADLAB.LADLAB.Graphics.AnimatedSprite;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;
import com.LADLAB.LADLAB.Graphics.SpriteSheet;

public class Chaser extends Mob {
	
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.skeleton_right, 16, 32, 2);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.skeleton_left, 16, 32, 2);
	
	private AnimatedSprite animSprite = right;
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.skeleton;
	}

    private void move() {
    	xa = 0;
    	ya = 0;
        ArrayList<Player> players = level.getPlayers(this, 50);
        if (players.size() > 0) {
        Player player = players.get(0);
    	if (x < player.getX()) xa+= speed;
    	if (x > player.getX()) xa-= speed;
    	if (y < player.getY()) ya+= speed;
    	if (y > player.getY()) ya-= speed;
        }
    	if (xa != 0 || ya != 0) {
			move(xa, ya);
		    walking = true;
		} else {
			walking = false;
		}
	}
	
	public void tick() {
		move();
		if (walking) animSprite.tick();
		else animSprite.setFrame(0);
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		}
		if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
	}

	public void paint(Screen screen) {
		sprite = animSprite.getSprite();
		screen.paintMob((int)(x - 16), (int)(y - 16), this);
	}

}

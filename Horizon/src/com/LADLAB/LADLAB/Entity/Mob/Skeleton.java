package com.LADLAB.LADLAB.Entity.Mob;

import com.LADLAB.LADLAB.Graphics.AnimatedSprite;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Graphics.Sprite;
import com.LADLAB.LADLAB.Graphics.SpriteSheet;

public class Skeleton extends Mob {
	
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.skeleton_right, 16, 32, 2);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.skeleton_left, 16, 32, 2);
	
	private AnimatedSprite animSprite = right;
	
	private int time = 0;
	private int xa = 0;
	private int ya = 0;
	
	public Skeleton(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.skeleton;
	}

	public void tick() {
		time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
		if (random.nextInt(2) == 0) {
			xa = 0;
			ya = 0;
		}
	}
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
		if (xa != 0 || ya != 0) {
			move(xa, ya);
		    walking = true;
		} else {
			walking = false;
		}
	}

	public void paint(Screen screen) {
		sprite = animSprite.getSprite();
		screen.paintMob((int)(x - 16), (int)(y - 16), sprite, false, 16);
	}

}

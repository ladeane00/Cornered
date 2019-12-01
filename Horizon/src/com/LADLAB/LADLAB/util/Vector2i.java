package com.LADLAB.LADLAB.util;

public class Vector2i {
	
	private int x, y;
	
	public Vector2i() {
		set(0, 0);
	}
	
	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}
	
	public Vector2i(int x, int y) {
		set(x, y);
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void test() {
		Vector2i player_position = new Vector2i(80, 40).setX(15);
		Vector2i mob_position = new Vector2i(player_position).setX(15);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}

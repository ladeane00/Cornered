package com.LADLAB.LADLAB.Level;

import java.awt.List;
import java.util.ArrayList;

import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Level.Tile.Tile;
import com.LADLAB.LADLAB.util.Vector2i;
import com.LADLAB.LADLAB.Entity.*;
import com.LADLAB.LADLAB.Entity.Mob.Player;
import com.LADLAB.LADLAB.Entity.Particle.Particle;
import com.LADLAB.LADLAB.Entity.Projectile.Projectile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).tick();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).tick();
		}
		
		remove();
		
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
	}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}
	
	private void time() {
		
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset + 7) >> 4;
			int yt = (y - c / 2 * size + yOffset + 7) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
		}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public void paint(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).paint(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).paint(screen);
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).paint(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).paint(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).paint(screen);
		}
		
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle)e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);	
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayerAt(int i) {
		return players.get(i);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	/*public ArrayList<Node> findPath(Vector2i start, Vector2i goal) {
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, )
	}*/
	
	/*private double getDistance(Vector2i tile, Vector2i goal) {
		double dx;
	}*/
	
	public ArrayList<Entity> getEntities(Entity e, int radius) {
		ArrayList<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
            int y = (int) entity.getY(); 
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
            	result.add(entity);
            }
		}
		return result;
	}
	
	public ArrayList<Player> getPlayers(Entity e, int radius) {
		ArrayList<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
            int y = (int) player.getY(); 
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
            	result.add(player);
            }
		}
		return result;
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xff00ff00) {
			return Tile.grass;
		}
		if (tiles[x + y * width] == 0xff0000ff) {
			return Tile.waterTile;
		}
		if (tiles[x + y * width] == 0xffff0000) {
			return Tile.brick;
		}
		if (tiles[x + y * width] == 0xff007d00) {
			return Tile.bush;
		}
		if (tiles[x + y * width] == 0xffffff00) {
			return Tile.sand;
		}
		if (tiles[x + y * width] == 0xff969696) {
			return Tile.tombstone;
		}
		if (tiles[x + y * width] == 0xff064405) {
			return Tile.darkGrass;
		}
		return Tile.voidTile;
	}

}

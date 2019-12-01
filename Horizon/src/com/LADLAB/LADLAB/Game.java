package com.LADLAB.LADLAB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.LADLAB.LADLAB.Entity.Mob.Player;
import com.LADLAB.LADLAB.Graphics.Screen;
import com.LADLAB.LADLAB.Input.KeyBoard;
import com.LADLAB.LADLAB.Input.Mouse;
import com.LADLAB.LADLAB.Level.Level;
import com.LADLAB.LADLAB.Level.SpawnLevel;
import com.LADLAB.LADLAB.Level.Tile.TileCoordinate;

public class Game extends JFrame implements Runnable {
private static final long serialVersionUID = 1L;
	


	public static boolean running = false;
	public final static int WIDTH = 300;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public final static int SCALE = 4;
	public final static String TITLE = "Horizon";
	
	public static int frames = 0;
	public static int updates = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Thread thread;
	private KeyBoard key;
	Mouse mouse;
	private Level level;
	private Player player;
	private JFrame frame;
	
	private Screen screen;
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
			tick();
			updates++;
			delta--;
			}
			paint();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " updates, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
 		
}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Game() {
		Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(d);
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = this;
		key = new KeyBoard();
		mouse = new Mouse();
		level = new SpawnLevel("/textures/SpawnLevel.png");
		TileCoordinate playerSpawn = new TileCoordinate(32, 32);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		level.add(player);
	}
	
	public void tick() {
		key.tick();
		level.tick();
	}
	
	public void paint() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
	screen.clear();
	double xScroll = player.getX() - screen.width / 2;
	double yScroll = player.getY() - screen.height / 2;
	level.paint((int)xScroll, (int)yScroll, screen);
	//screen.paintSheet(40, 40, SpriteSheet.player_up, false);
		
	for (int i = 0; i < pixels.length; i++) {
		pixels[i] = screen.pixels[i];
	}
	
	Graphics g = buffer.getDrawGraphics();
	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	
	g.setColor(Color.WHITE);
	g.fillRect(Mouse.getX(), Mouse.getY(), 16, 16);
	g.drawString("" + Mouse.getB(), 40, 40);

	g.dispose();
	buffer.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setResizable(false);
		game.setTitle(TITLE);
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		game.addKeyListener(game.key);
		game.addMouseListener(game.mouse);
		game.addMouseMotionListener(game.mouse);
		game.start();
	}
}

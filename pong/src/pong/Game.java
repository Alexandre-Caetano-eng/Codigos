package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH=240;
	public static int HEIGHT=120;
	public static int SCALE=3;
	public static Player player;
	public static Placar placar;
	public static Enemy enemy;
	public static Ball ball;
	private Thread thread;
	private BufferedImage Image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(0,140);
		enemy = new Enemy(710,140);
		ball = new Ball(350,170);
		placar = new Placar(350,35);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addKeyListener(game);
		game.start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
		placar.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = Image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g = bs.getDrawGraphics();
		
		g.drawImage(Image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		placar.render(g);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		while(true) {
			long now = System.nanoTime();
			delta+= (now -lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				tick();
				render();
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				timer+=1000;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.up=true;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down=true;
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			player.down=true;
		}else if(e.getKeyCode()==KeyEvent.VK_W) {
			player.up=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.up=false;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down=false;
		}else if(e.getKeyCode()==KeyEvent.VK_W) {
			player.up=false;
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			player.down=false;
		}
		
	}

}

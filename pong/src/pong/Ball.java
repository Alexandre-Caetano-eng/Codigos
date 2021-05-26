package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	Random rand = new Random();
	public boolean down,up;
	private double x=0, dx, dy, speed=0.5d;
	public static double y=0;
	private int width=10,heigth=10;
	private Rectangle reactB = new Rectangle((int)x, (int)y, width, heigth);
	public Ball(int x, int y1){
		this.x=x;
		y=y1;
		geraDX();
		geraDY();
	}
	
	public void geraDX() {
		dx=rand.nextGaussian();
	}
	
	public void geraDY() {
		dy=rand.nextGaussian();
	}

	public void tick() {
		
		if((y+heigth)>Game.HEIGHT*3) {
			dy=-dy;
		}else if(y*heigth<0) {
			dy=-dy;
		}
		x+=dx*speed;
		y+=dy*speed;
		reactB.setLocation((int)(x+(dx*speed)), (int)(y+(dy*speed)));
		if(reactB.x<11) {
			if((reactB.y+width)>=(Game.player.reactP.y)&&reactB.y<=(Game.player.reactP.y+Game.player.heigth)||(reactB.y)>=(Game.player.reactP.y)&&(reactB.y+width)<=(Game.player.reactP.y+Game.player.heigth)) {
				dx=-dx;
				speed+=0.2d;
			}
		}else if(reactB.x>700) {
			if((reactB.y+width)>=(Game.enemy.reactE.y)&&reactB.y<=(Game.enemy.reactE.y+Game.enemy.heigth)||(reactB.y)>=(Game.enemy.reactE.y)&&(reactB.y+width)<=(Game.enemy.reactE.y+Game.enemy.heigth)) {
				dx=-dx;
				speed+=0.2d;
			}
		}
		if(reactB.x<2||reactB.x>718) {
			if((int)reactB.x<7) {
				Game.placar.ponto(1);
			}if((int)reactB.x>710) {
				Game.placar.ponto(0);
			}
			x=350;
			y=170;
			speed=0.5d;
			dx=-dx;
			geraDY();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, heigth);
	}
}

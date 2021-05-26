package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	public boolean down,up;
	public double y=0, x=0;
	public int width=10,heigth=60;
	public Rectangle reactE = new Rectangle((int)x, (int)y, width, heigth);
	public Enemy(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void tick() {
		//y+= Ball.y-y-6;
		reactE.setLocation((int)x, (int)y);
		if(Ball.y>reactE.y+(width)) {
			y+=1.7;
		}else if(Ball.y<reactE.y+(width)) {
			y-=1.7;
		}
		if((y+heigth)>Game.HEIGHT*3) {
			y = Game.HEIGHT*3 - heigth;
		}else if(y*heigth<0) {
			y = 0;
		}
		
		/*if() {
			
		}*/
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, heigth);
	}
}

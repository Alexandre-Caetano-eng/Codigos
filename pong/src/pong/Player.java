package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	public boolean down,up;
	public int x=0, y=0, width=10,heigth=60;
	public Rectangle reactP = new Rectangle((int)x, (int)y, width, heigth);
	public Player(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void tick() {
		if(down) {
			y+=2;
		}else if(up) {
			y-=2;
		}
		if((y+heigth)>Game.HEIGHT*3) {
			y = Game.HEIGHT*3 - heigth;
		}else if(y*heigth<0) {
			y = 0;
		}
		reactP.setLocation(x, y);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, heigth);
	}
}

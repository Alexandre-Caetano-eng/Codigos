package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Placar {
	private int x,y, p=0, e=0, espacop=1;
	private String placarP="0", placarE="0";
	Font newfonte = new Font( "SansSerif", Font.PLAIN, 30 );
	public Placar(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void ponto(int i) {
		if(i==0) {
			this.p+=1;
			placarP=String.valueOf(p);
			espacop=placarP.length();
		}if(i==1) {
			this.e+=1;
			placarE=String.valueOf(e);
		}
	}
	public void tick() {
	
	}
	
	public void render(Graphics g) {
		g.setFont(newfonte);
		g.setColor(Color.BLUE);
		g.drawString(placarP, x-10-(16*espacop), y);
		g.setColor(Color.YELLOW);
		g.drawString("X", x, y);
		g.setColor(Color.RED);
		g.drawString(placarE, x+26, y);
	}

}

package com.ignatieff.spiderbro;

import java.awt.Frame;
import java.awt.Window;
import java.util.Timer;
import java.util.TimerTask;

public class SpiderWindow extends Window {
	
	private static final long serialVersionUID = -7758199506052146297L;
	private TimerTask timer;
	
	public SpiderGraphics g;
	
	public SpiderWindow(Frame owner) {
		super(owner);

		g = new SpiderGraphics();
		this.add(g);
		
		timer = new TimerTask(){

			@Override
			public void run() {
				g.repaint();
			}
			
		};
		
	}
	
	public void addSpider(){
		g.addSpider(getWidth(), getHeight());

		new Timer().scheduleAtFixedRate(timer, 0, 40);
	}
	
}

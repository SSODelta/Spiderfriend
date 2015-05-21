package com.ignatieff.spiderbro;

import java.awt.Color;

public class SpiderEngine {

	private SpiderWindow w;
	
	public SpiderEngine(){
		w=new SpiderWindow(null);
		
	}
	public void start(){
		
		//Create window
		w.setAlwaysOnTop(true);
		w.setBounds(w.getGraphicsConfiguration().getBounds());
		w.setBackground(new Color(0, true));
		w.setVisible(true);
		w.addSpider();
	}

}

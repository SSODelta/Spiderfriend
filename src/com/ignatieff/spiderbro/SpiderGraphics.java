package com.ignatieff.spiderbro;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpiderGraphics extends JPanel {
	
	private static final long  serialVersionUID = 5927638231971556923L;
	private static final Color EMPTY            = new Color(0,0,0,0);
	
	public BufferedImage[] SPIDER_IMG;
	public int currentImg, width, height;
	public Spiderbro spiderBot;
	
	public void addSpider(int w, int h){
		width=w;
		height=h;
		
		spiderBot = new Spiderbro(width, height);
		spiderBot.leapRandom(100);
	}
	
	public SpiderGraphics(){
		setBackground(EMPTY);
		currentImg = 0;
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void loadImages() throws IOException{
		SPIDER_IMG = new BufferedImage[6];
		for(int i=0; i<6; i++){
			SPIDER_IMG[i] = ImageIO.read(new File("spiderbro"+i+".png"));
		}
	}
	
	public BufferedImage rotate(BufferedImage bufferedImage, double rot){
		AffineTransform tx = new AffineTransform();
	    tx.rotate(rot, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_BILINEAR);
	    return op.filter(bufferedImage, null);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(spiderBot==null)return;
		
		//Get the new Image
		if(spiderBot.hasMoved())currentImg++;currentImg%=6;
		BufferedImage img = SPIDER_IMG[currentImg];
		
		//Clear current screen contents
		Point pos = spiderBot.getPosition();
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setColor(EMPTY);
		g.fillRect(pos.x, pos.y, img.getWidth(), img.getHeight());
		
		//Update the spiderbot
		spiderBot.tick();
		pos = spiderBot.getPosition();
		
		//And rotate it
		img = rotate(img,Math.PI/2+spiderBot.getRotation());
		g.drawImage(img, pos.x, pos.y, null);
		
		g.dispose();
		
		update();
		
	}
	
	public void update(){
		Point m = MouseInfo.getPointerInfo().getLocation() ;
		double d = dist(spiderBot.getPosition(),m);
		if(d<100 && !spiderBot.hasMoved())
			spiderBot.leapRandom(100);
	}
	
	private static double dist(Point a, Point b){
		return Math.sqrt(
				Math.pow(a.x-b.x+159/2, 2)+
				Math.pow(a.y-b.y+152/2, 2));
	}
}

package com.ignatieff.spiderbro;

import java.awt.Point;


public class Spiderbro {
	
	private int w,h;
	
	private boolean moved;
	
	private int t, tMax;
	
	private double 	rotation, 
					x,  y,
					dx, dy;
	
	
	public Spiderbro(int width, int height){
		x=width/2;
		y=height/2;
		
		w=width;
		h=height;
		
		moved=false;
		
		dx=0; dy=0;
	}
	
	public void teleport(int gx, int gy){
		x=gx;
		y=gy;
	}
	
	public void teleport(Point p){
		teleport(p.x,p.y);
	}
	
	public void leap(int gx, int gy, int ticks){
		dx = -((double)(x-gx))/(double)ticks;
		dy = -((double)(y-gy))/(double)ticks;
		rotation = Math.atan2(dy, dx);
		tMax=ticks;
		t=0;
	}
	
	public boolean hasMoved(){
		return moved;
	}
	
	public void leap(Point p, int ticks){
		leap(p.x,p.y,ticks);
	}
	
	public void leapRandom(int ticks){
		leap(	randomPoint(),
				ticks);
	}
	
	private Point randomPoint(){
		return new Point(random(w),random(h));
	}
	
	private int random(int max){
		return (int)(Math.random()*max);
	}
	
	public void tick(){
		moved=false;
		if(t>=tMax)return;
		
		x+=dx;
		y+=dy;
		
		moved=true;
		t++;
	}
	
	public Point getPosition(){
		return new Point((int)x,(int)y);
	}

	public double getRotation(){
		return rotation;
	}
}

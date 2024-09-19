package com.tedu.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

//子弹类

public class PlayFile extends ElementObj{

	
	private int attack; //攻击力
	private int moveNum = 3;
	private String fx; 
	
	
	public PlayFile() {
	} 

	public ElementObj createElement(String str) {
		
		String[] split = str.split(",");
		for (String str1 : split) {
			String[] split2 = str1.split(":");
			switch (split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1])); break;
			case "y": this.setY(Integer.parseInt(split2[1])); break;
			case "f": this.fx = split2[1]; break;
			}
		}
		this.setType(6);
		this.setH(10);
		this.setW(10);
		return this;
	}
	

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.setColor(Color.RED);
		g.fillOval(this.getX(), this.getY(), 10, 10);
	}
	
	@Override
	protected void move() {
		// TODO 自动生成的方法存根
		
		if (this.getX() < 0 || this.getX() > 800 || this.getY() < 0 || this.getY() > 600) {
			this.setLive(false);
			return;
		}
		
		
		switch (this.fx) {
		case "up":this.setY(this.getY() - this.moveNum); break;
		case "left":this.setX(this.getX() - this.moveNum); break;
		case "right":this.setX(this.getX() + this.moveNum); break;
		case "down":this.setY(this.getY() + this.moveNum); break;
		}
	}
	@Override
	public void die() {
		
	}
	
}

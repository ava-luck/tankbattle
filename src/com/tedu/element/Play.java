package com.tedu.element;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.controller.GameThread;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

public class Play extends ElementObj{

	
	private boolean left = false;
	private boolean up = false;
	private boolean right = false;
	private boolean down = false;
	
	private int lx = 0, ly = 0;
	
	private String fx = "up";
	private boolean pkType = false; //攻击状态， true攻击
	
	public Play() {
		
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(new Integer(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setH(icon2.getIconHeight());
		this.setW(icon2.getIconWidth());
		this.setIcon(icon2);
		return this;
	}
	
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);
		// TODO 自动生成的构造函数存根
	}

	
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), getW(), this.getH(), null);
		
	}
	
	public void keyClick(boolean bl, int key) {
		if (bl) {
			switch(key) {
			case 37: this.fx = "left"; this.right = false; this.left = true; break;
			case 38: this.fx = "up";this.down = false; this.up = true; break;
			case 39: this.fx = "right";this.left = false; this.right = true; break;
			case 40: this.fx = "down";this.up = false;this.down = true; break;
			case 32: this.pkType = true; break;
			}
		}else {
			switch(key) {
			case 37:  this.left = false; break;
			case 38: this.up = false; break;
			case 39: this.right = false; break;
			case 40: this.down = false; break;
			case 32: this.pkType = false; break;
			}
		}
		
	}
	@Override
	public void move() {
		
		if (this.left && this.getX() > 0) {
			this.setX(this.getX() - 2);
			lx = 2; 
		}
		if (this.up && this.getY() > 0) {
			this.setY(this.getY() - 2);
			ly = 2;
		}
		if (this.right && this.getX() < 800 - this.getW()) {
			this.setX(this.getX() + 2);
			lx = -2;
		}
		if (this.down && this.getY() < 580 - this.getH()) {
			this.setY(this.getY() + 2);
			ly = -2;
		}
	}
	
	@Override
	public void rmove() {
		this.setX(this.getX() + lx);
		this.setY(this.getY() + ly);
		lx = ly = 0; 
	}
	
	
	
	@Override
	protected void updateImage() {
		this.setIcon(GameLoad.imgMap.get(fx));
	}
	
	@Override
	protected void add() {
		
		if (!this.pkType)
			return;
		this.pkType = false;
		// TODO 自动生成的方法存根
		ElementObj element = new PlayFile().createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
	}
	@Override
	public String toString() {
		int x = this.getX();
		int y = this.getY();
		switch (this.fx) {
		case "up":x += 13;break;
		case "left": y += 13;break;
		case "right": x += 50; y += 13; break;
		case "down": x += 13; y += 50; break;
		}
		return "x:"+x + ",y:" + y + ",f:" + this.fx;
	}
	
	

}

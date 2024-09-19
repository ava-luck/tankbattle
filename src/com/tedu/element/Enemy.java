package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

public class Enemy extends ElementObj{
 

	private boolean left = false;
	private boolean up = false;
	private boolean right = false;
	private boolean down = false;
	
	private int lx = 0, ly = 0;
	
	private String fx = "down";
	
	private long time = 0;
	
	private Random random = new Random();
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	
	 
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(new Integer(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setH(icon2.getIconHeight());
		this.setW(icon2.getIconWidth());
		//System.out.println(this.getH() + "   " + this.getW());
		this.setIcon(icon2);
		this.setType(7);
		return this;
	}
	
	@Override
	public void move() {
		setfx();
		if (this.left && this.getX() > 0) {
			this.setX(this.getX() - 1);
			lx = 1; 
		}
		if (this.up && this.getY() > 0) {
			this.setY(this.getY() - 1);
			ly = 1;
		}
		if (this.right && this.getX() < 800 - this.getW()) {
			this.setX(this.getX() + 1);
			lx = -1;
		}
		if (this.down && this.getY() < 580 - this.getH()) {
			this.setY(this.getY() + 1);
			ly = -1;
		}
	}
	
	public synchronized void setfx() {
		//boss 390 540
		time++;
		time %= 300;
		if (time != 0) return;
		this.down = this.left = this.right = this.up = false;
		if (random.nextInt(100) <= 30) {
			if (random.nextInt(10) >= 2) {
				if (this.getX() <= 390) {
					this.fx = "right";
					this.right = true;
				}
				else {
					this.fx = "left";
					this.left = true;
				}
			}
			else {
				if (this.getX() > 390) {
					this.fx = "right";
					this.right = true;
				}
				else {
					this.fx = "left";
					this.left = true;
				}
			}
				
		}
		else {
			if (random.nextInt(10) >= 2) {
				this.down = true;
				this.up = false;
				this.fx = "down";
			}else {
				this.up = true;
				this.down = false;
				this.fx = "up";
			}
				
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
		this.setIcon(GameLoad.imgMap.get("bot_" + fx));
	}
	
	@Override
	protected void add() {
		// TODO 自动生成的方法存根
		//time++;
		if (time % 70 != 0 || random.nextBoolean() == true) return;
		ElementObj element = new EnemyFile().createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.ENEMYFILE);
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
